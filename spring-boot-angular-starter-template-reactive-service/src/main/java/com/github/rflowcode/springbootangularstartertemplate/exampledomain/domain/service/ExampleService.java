package com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.service;

import com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.api.inbound.IExampleInboundApi;
import com.github.rflowcode.springbootangularstartertemplate.exampledomain.domain.model.ExampleResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
class ExampleService implements IExampleInboundApi {

    private final SecureRandom secureRandom;

    public ExampleService() {
        secureRandom = new SecureRandom();
    }

    @Override
    public Mono<ExampleResult> getRandomNumber() {
        return Mono.fromSupplier(secureRandom::nextInt)
                .map(BigDecimal::valueOf)
                .map(ExampleResult::new);
    }
}
