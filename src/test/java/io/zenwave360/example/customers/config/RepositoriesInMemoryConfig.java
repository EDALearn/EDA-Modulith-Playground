package io.zenwave360.example.customers.config;

import io.zenwave360.example.customers.*;
import io.zenwave360.example.customers.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final CustomerRepository customerRepository = new CustomerRepositoryInMemory();

    @Bean
    @Primary
    public <T extends CustomerRepository> T customerRepository() {
        return (T) customerRepository;
    }

}
