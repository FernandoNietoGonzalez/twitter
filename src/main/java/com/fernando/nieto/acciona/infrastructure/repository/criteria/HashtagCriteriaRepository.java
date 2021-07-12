package com.fernando.nieto.acciona.infrastructure.repository.criteria;

import com.fernando.nieto.acciona.domain.exception.InvalidDataException;
import com.fernando.nieto.acciona.domain.model.filter.HashtagFilter;
import com.fernando.nieto.acciona.infrastructure.repository.entity.HashtagEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Component
public class HashtagCriteriaRepository {

    @Autowired
    private EntityManager em;

    public List<HashtagEntity> getHashtagsByFiler(final HashtagFilter filter) {
        try {
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<HashtagEntity> cq = cb.createQuery(HashtagEntity.class);
            final Root<HashtagEntity> root = cq.from(HashtagEntity.class);
            if (filter.getOrderBy() != null) {
                cq.orderBy(cb.desc(root.get(filter.getOrderBy().name().toLowerCase())));
            }
            return em.createQuery(cq)
                    .setFirstResult((filter.getPageNumber() - 1) * filter.getPageSize())
                    .setMaxResults(filter.getPageSize())
                    .getResultList();

        } catch (final Exception e) {
            final String error = format("Unexpected error finding hashtags by %s: %s", filter.toString(), e.getMessage());
            log.error(error);
            throw new InvalidDataException(error);
        }
    }
}
