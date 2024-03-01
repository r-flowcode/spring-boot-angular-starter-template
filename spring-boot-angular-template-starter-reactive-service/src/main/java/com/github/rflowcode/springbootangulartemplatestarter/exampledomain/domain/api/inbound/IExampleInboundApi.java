package com.github.rflowcode.springbootangulartemplatestarter.exampledomain.domain.api.inbound;

import com.github.rflowcode.springbootangulartemplatestarter.exampledomain.domain.model.ExampleResult;
import reactor.core.publisher.Mono;

public interface IExampleInboundApi {

    Mono<ExampleResult> getRandomNumber();
}
