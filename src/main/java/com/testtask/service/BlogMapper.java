package com.testtask.service;

import com.testtask.model.BlogEntity;
import com.testtask.service.request.BlogCreateRequest;
import com.testtask.service.response.BlogTo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    BlogTo toBlogTo(BlogEntity blogEntity);

    List<BlogTo> toBlogTo(List<BlogEntity> blogEntity);

    void toBlogEntity(@MappingTarget BlogEntity blogEntity, BlogCreateRequest blogCreateRequest);

    BlogEntity toBlogEntity(BlogCreateRequest blogCreateRequest);
}
