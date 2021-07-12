package com.twitter.infrastructure.repository.adapter;

import com.twitter.domain.model.Hashtag;
import com.twitter.infrastructure.repository.entity.HashtagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HashtagRepositoryAdapter {

    HashtagRepositoryAdapter INSTANCE = Mappers.getMapper(HashtagRepositoryAdapter.class);

    List<Hashtag> adapt(List<HashtagEntity> entities);

    Hashtag adapt(HashtagEntity entity);

    List<HashtagEntity> adaptToRepo(List<Hashtag> hashtag);

    HashtagEntity adaptToRepo(Hashtag hashtag);
}