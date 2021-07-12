package com.twitter.infrastructure.repository.adapter;

import com.twitter.domain.model.Tweet;
import com.twitter.infrastructure.repository.entity.TweetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = HashtagRepositoryAdapter.class)
public interface TweetRepositoryAdapter {

    TweetRepositoryAdapter INSTANCE = Mappers.getMapper(TweetRepositoryAdapter.class);

    @Mapping(target = "hashtags", ignore = true)
    @Mapping(target = "createdAt", source = "created_at")
    Tweet adapt(TweetEntity entity);

    @Mapping(target = "created_at", source = "createdAt")
    TweetEntity adaptToRepo(Tweet tweet);

}
