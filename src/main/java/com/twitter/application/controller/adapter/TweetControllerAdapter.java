package com.twitter.application.controller.adapter;

import com.twitter.domain.model.Tweet;
import com.twitter.model.TweetApiDto;
import com.twitter.model.UpdateTweetApiDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TweetControllerAdapter {

    TweetControllerAdapter INSTANCE = Mappers.getMapper(TweetControllerAdapter.class);

    @Mapping(target = "originId", source = "tweetId")
    @Mapping(target = "userName", source = "user")
    @Mapping(target = "createdAt", ignore = true)
    TweetApiDto adapt(Tweet tweet);

    @AfterMapping
    default void setDate(final Tweet source, @MappingTarget final TweetApiDto target) {
        if (source.getCreatedAt() != null) {
            target.setCreatedAt(OffsetDateTime.of(source.getCreatedAt(), ZoneOffset.UTC));
        }
    }
    @Mapping(target = "hashtags", ignore = true)
    @Mapping(target = "validated", source = "updateTweet.validated")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "text", ignore = true)
    @Mapping(target = "tweetId", ignore = true)
    @Mapping(target = "user", ignore = true)
    Tweet adapt(UpdateTweetApiDto updateTweet, Long id);

}
