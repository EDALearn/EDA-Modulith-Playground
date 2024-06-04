package io.zenwave360.example.restaurants.core.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.restaurants.config.*;
import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.implementation.mappers.*;
import io.zenwave360.example.restaurants.core.inbound.*;
import io.zenwave360.example.restaurants.core.inbound.dtos.*;
import io.zenwave360.example.restaurants.core.outbound.mongodb.*;
import io.zenwave360.example.restaurants.infrastructure.mongodb.inmemory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Acceptance Test for RestaurantOrdersService. */
public class RestaurantOrdersServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    RestaurantOrdersServiceImpl restaurantOrdersService = context.restaurantOrdersService();

    KitchenOrderRepositoryInMemory kitchenOrderRepository = context.kitchenOrderRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void createKitchenOrderTest() {
        var input = new KitchenOrderInput();
        // TODO fill input data
        // input.setOrderId("");
        // input.setRestaurantId("");
        // input.setDate(new LocalDateTime());
        // input.setItems(new MenuItem());
        // input.setStatus(KitchenOrderStatus.values()[0]);
        // input.setCustomer(new CustomerDetails());
        var kitchenOrder = restaurantOrdersService.createKitchenOrder(input);
        assertNotNull(kitchenOrder.getId());
        assertTrue(kitchenOrderRepository.containsEntity(kitchenOrder));
    }

    @Test
    void onOrderStatusUpdatedTest() { // TODO: implement this test
    }

    @Test
    void updateKitchenOrderStatusTest() { // TODO: implement this test
    }

    @Test
    void searchKitchenOrdersTest() { // TODO: implement this test
    }

}
