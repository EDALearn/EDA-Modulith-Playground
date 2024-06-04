package io.zenwave360.example.customers.config;

import io.zenwave360.example.customers.*;
import io.zenwave360.example.customers.events.*;
import io.zenwave360.example.customers.model.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    private ApplicationEventPublisher applicationEventPublisher = new ApplicationEventPublisher() {
        @Override
        public void publishEvent(Object event) {
            publishedEvents.add(event);
        }
    };

    protected final CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository(),
            applicationEventPublisher);

    @Bean
    public <T extends CustomerService> T customerService() {
        return (T) customerService;
    }

    static List<Customer> _customers;

    public void reloadTestData() {
        var testDataLoader = new TestDataLoader(List.of(Customer.class, Address.class));
        var customers = _customers != null ? _customers
                : testDataLoader.loadCollectionTestDataAsObjects(Customer.class);
        customerRepository().deleteAll();
        customerRepository().saveAll(customers);
    }

    private List<Object> publishedEvents = new ArrayList<>();

    public List<Object> getPublishedEvents() {
        return publishedEvents;
    }

}
