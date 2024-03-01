package com.github.rflowcode.springbootangulartemplatestarter.exampledomain.adapter.rest.inbound;

import com.github.rflowcode.springbootangulartemplatestarter.exampledomain.domain.api.inbound.IExampleInboundApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
class ExampleRestServiceV1 implements ExampleApi {

    private final IExampleDtoMapper mapper;
    private final IExampleInboundApi exampleInboundApi;

    @Override
    public Mono<ResponseEntity<ExampleResponseDto>> getRandomNumber(ServerWebExchange exchange) {
        return exampleInboundApi.getRandomNumber()
                .map(mapper::toExampleResponseDto)
                .map(ResponseEntity::ok);
    }
}
