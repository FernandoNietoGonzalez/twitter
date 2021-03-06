package com.twitter.application.controller.adapter;

import com.twitter.domain.model.Hashtag;
import com.twitter.model.HashtagApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HashtagControllerAdapter {

    HashtagControllerAdapter INSTANCE = Mappers.getMapper(HashtagControllerAdapter.class);

    HashtagApiDto adapt(Hashtag Hashtag);

}
