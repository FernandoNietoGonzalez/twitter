package com.fernando.nieto.acciona.infrastructure.repository;


import com.fernando.nieto.acciona.infrastructure.repository.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TweetJpaRepository extends JpaRepository<TweetEntity, Long> {

    Optional<TweetEntity> findByTweetId(Long tweetId);
}
