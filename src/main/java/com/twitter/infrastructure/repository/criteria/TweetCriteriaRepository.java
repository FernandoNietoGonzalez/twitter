package com.fernando.nieto.acciona.infrastructure.repository.criteria;

import com.fernando.nieto.acciona.domain.model.filter.TweetFilter;
import com.fernando.nieto.acciona.infrastructure.repository.entity.TweetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class TweetCriteriaRepository {

    private static final String USER_NAME = "user";
    private static final String VALIDATED = "validated";

    @Autowired
    private EntityManager em;

    public List<TweetEntity> getTweetsByFiler(final TweetFilter filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<TweetEntity> cq = cb.createQuery(TweetEntity.class);
        final Root<TweetEntity> root = cq.from(TweetEntity.class);
        final List<Predicate> predicates = new ArrayList<>();
        addPredicatesByFilterToCriteria(filter, cb, root, predicates);
        cq.where(predicates.toArray(new Predicate[] {}));
        return em.createQuery(cq)
                .setFirstResult((filter.getPageNumber() - 1) * filter.getPageSize())
                .setMaxResults(filter.getPageSize())
                .getResultList();
    }

    private void addPredicatesByFilterToCriteria(
            final TweetFilter filter,
            final CriteriaBuilder cb,
            final Root<TweetEntity> root,
            final List<Predicate> predicates
    ) {
        if (filter.getUserName() != null) {
            predicates.add(cb.equal(root.get(USER_NAME), filter.getUserName()));
        }
        if (filter.getValidated() != null) {
            predicates.add(cb.equal(root.get(VALIDATED), filter.getValidated()));
        }
    }
}
