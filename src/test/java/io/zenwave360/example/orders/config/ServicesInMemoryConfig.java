package io.zenwave360.example.orders.config;

import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.domain.events.*;
import io.zenwave360.example.orders.core.implementation.*;
import io.zenwave360.example.orders.core.inbound.*;
import java.util.ArrayList;
import java.util.List;

import io.zenwave360.example.orders.core.outbound.events.EventPublisher;
import io.zenwave360.example.orders.infrastructure.events.InMemoryEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    private InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();

    protected final OrdersServiceImpl ordersService = new OrdersServiceImpl(customerOrderRepository(),
            null, null, eventPublisher);

    @Bean
    public InMemoryEventPublisher  eventPublisher() {
        return eventPublisher;
    }

    @Bean
    public <T extends OrdersService> T ordersService() {
        return (T) ordersService;
    }

    static List<CustomerOrder> _customerOrders;

    public void reloadTestData() {
        var testDataLoader = new TestDataLoader(
                List.of(CustomerOrder.class, Customer.class, Address.class, Restaurant.class, OrderItemInput.class));
        var customerOrders = _customerOrders != null ? _customerOrders
                : testDataLoader.loadCollectionTestDataAsObjects(CustomerOrder.class);
        customerOrderRepository().deleteAll();
        customerOrderRepository().saveAll(customerOrders);
        eventPublisher.getEvents().clear();
    }


}
