package com.example.springwebfluxproject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    void monoTest() {
        Mono<?> monoString = Mono.just("Hello")
//                .then(Mono.error(new RuntimeException("Some exception is throwm")))
                .log();
        monoString.subscribe(System.out::println);
    }

    @Test
    void fluxTest() {
        Flux<String> fluxString = Flux.just("Hello", "World", "How", "are", "you")
//                .concatWithValues("!!!")
                .log();
        fluxString.subscribe(System.out::println);
    }
}
