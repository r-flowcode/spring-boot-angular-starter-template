package com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.api.inbound;

import com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.model.ExampleResult;
import reactor.core.publisher.Mono;

public interface IExampleInboundApi {

    Mono<ExampleResult> getRandomNumber();
}
