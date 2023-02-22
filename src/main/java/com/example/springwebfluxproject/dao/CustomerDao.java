package com.example.springwebfluxproject.dao;

import com.example.springwebfluxproject.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDao::sleepThread)
                .peek(i -> System.out.println("count: " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofMillis(1000))
                .doOnNext(i -> System.out.println("count: " + i))
                .map(i -> new Customer(i, "customer_" + i));
    }

    public Flux<Customer> getCustomerList() {
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("count: " + i))
                .map(i -> new Customer(i, "customer_" + i));
    }

    private static void sleepThread(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
