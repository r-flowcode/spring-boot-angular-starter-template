package com.github.rflowcode.springbootangularstartertemplate.exampledomain.adapter.rest.inbound;

import com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.model.ExampleResult;
import org.mapstruct.Mapper;

@Mapper
interface IExampleDtoMapper {

    ExampleResponseDto toExampleResponseDto(ExampleResult source);
}
