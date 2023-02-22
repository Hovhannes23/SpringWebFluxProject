package com.example.springwebfluxproject.router;

import com.example.springwebfluxproject.handler.CustomerHandler;
import com.example.springwebfluxproject.handler.CustomerHandlerStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Autowired
    private CustomerHandler handler;
    @Autowired
    private CustomerHandlerStream handlerStream;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", handlerStream::getCustomers)
                .GET("/router/customers/{id}", handler::findCustomer)
                .POST("/router/customers/save", handler::saveCustomer)
                .build();
    }
}
