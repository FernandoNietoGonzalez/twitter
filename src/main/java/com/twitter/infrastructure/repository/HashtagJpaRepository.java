package com.twitter.infrastructure.repository;


import com.twitter.infrastructure.repository.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagJpaRepository extends JpaRepository<HashtagEntity, Long> {

    Optional<HashtagEntity> findByText(String text);


}
