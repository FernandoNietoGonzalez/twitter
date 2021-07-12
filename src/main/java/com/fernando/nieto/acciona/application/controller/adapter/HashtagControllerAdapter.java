package com.fernando.nieto.acciona.application.controller.adapter;

import com.fernando.nieto.acciona.domain.model.Hashtag;
import com.fernando.nieto.accionaapi.model.HashtagApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HashtagControllerAdapter {

    HashtagControllerAdapter INSTANCE = Mappers.getMapper(HashtagControllerAdapter.class);

    HashtagApiDto adapt(Hashtag Hashtag);

}
