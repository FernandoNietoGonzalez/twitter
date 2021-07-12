package com.fernando.nieto.acciona.infrastructure.twitter.adapter;

import com.fernando.nieto.acciona.domain.model.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import twitter4j.Status;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = HashtagAdapter.class)
public interface TweetAdapter {

    TweetAdapter INSTANCE = Mappers.getMapper(TweetAdapter.class);

    @Mapping(target = "hashtags", source = "hashtagEntities")
    @Mapping(target = "location", source = "user.location")
    @Mapping(target = "validated", ignore = true)
    @Mapping(target = "language", source = "lang")
    @Mapping(target = "user", source = "user.name")
    @Mapping(target = "tweetId" , source = "id")
    @Mapping(target = "id", ignore = true)
    Tweet adapt(Status status);

}
