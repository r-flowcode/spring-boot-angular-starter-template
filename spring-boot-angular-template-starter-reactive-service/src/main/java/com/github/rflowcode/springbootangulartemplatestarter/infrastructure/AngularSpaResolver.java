package com.github.rflowcode.springbootangulartemplatestarter.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
class AngularSpaResolver implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (!path.startsWith("/rest/") && path.matches("[^\\\\.]*")) {
            return chain.filter(
                    exchange.mutate().request(exchange.getRequest().mutate().path("/index.html").build()
                    ).build());
        }

        return chain.filter(exchange);
    }
}
