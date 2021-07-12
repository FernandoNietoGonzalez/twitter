package com.fernando.nieto.acciona.infrastructure.repository;


import com.fernando.nieto.acciona.infrastructure.repository.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagJpaRepository extends JpaRepository<HashtagEntity, Long> {

    Optional<HashtagEntity> findByText(String text);


}
