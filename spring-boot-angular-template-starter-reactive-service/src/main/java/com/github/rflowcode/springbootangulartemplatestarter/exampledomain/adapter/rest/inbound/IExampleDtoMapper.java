package com.github.rflowcode.springbootangulartemplatestarter.exampledomain.adapter.rest.inbound;

import com.github.rflowcode.springbootangulartemplatestarter.exampledomain.domain.model.ExampleResult;
import org.mapstruct.Mapper;

@Mapper
interface IExampleDtoMapper {

    ExampleResponseDto toExampleResponseDto(ExampleResult source);
}
