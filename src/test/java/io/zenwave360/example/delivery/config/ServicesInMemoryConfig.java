package io.zenwave360.example.delivery.config;

import io.zenwave360.example.delivery.core.domain.*;
import io.zenwave360.example.delivery.core.domain.events.*;
import io.zenwave360.example.delivery.core.implementation.*;
import io.zenwave360.example.delivery.core.inbound.*;
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

    protected final DeliveryServiceImpl deliveryService = new DeliveryServiceImpl(deliveryRepository(),
            applicationEventPublisher);

    @Bean
    public <T extends DeliveryService> T deliveryService() {
        return (T) deliveryService;
    }

    static List<Delivery> _deliveries;

    public void reloadTestData() {
        var testDataLoader = new TestDataLoader(
                List.of(Delivery.class, Customer.class, Address.class, Restaurant.class, OrderItem.class));
        var deliveries = _deliveries != null ? _deliveries
                : testDataLoader.loadCollectionTestDataAsObjects(Delivery.class);
        deliveryRepository().deleteAll();
        deliveryRepository().saveAll(deliveries);
    }

    private List<Object> publishedEvents = new ArrayList<>();

    public List<Object> getPublishedEvents() {
        return publishedEvents;
    }

}
