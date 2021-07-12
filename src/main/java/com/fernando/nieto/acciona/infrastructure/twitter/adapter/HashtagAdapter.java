package com.fernando.nieto.acciona.infrastructure.twitter.adapter;

import com.fernando.nieto.acciona.domain.model.Hashtag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import twitter4j.HashtagEntity;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HashtagAdapter {

    HashtagAdapter INSTANCE = Mappers.getMapper(HashtagAdapter.class);

    List<Hashtag> adapt(List<HashtagEntity> status);

    @Mapping(target = "counter", ignore = true)
    @Mapping(target = "id", ignore = true)
    Hashtag adapt(HashtagEntity status);
}
