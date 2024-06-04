package io.zenwave360.example.orders.core.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.orders.config.*;
import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.implementation.mappers.*;
import io.zenwave360.example.orders.core.inbound.*;
import io.zenwave360.example.orders.core.inbound.dtos.*;
import io.zenwave360.example.orders.core.outbound.mongodb.*;
import io.zenwave360.example.orders.infrastructure.mongodb.inmemory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Acceptance Test for OrdersService. */
public class OrdersServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    OrdersServiceImpl ordersService = context.ordersService();

    CustomerOrderRepositoryInMemory customerOrderRepository = context.customerOrderRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void getCustomerOrderTest() {
        var id = "1"; // TODO fill id
        var customerOrder = ordersService.getCustomerOrder(id);
        assertTrue(customerOrder.isPresent());
    }

    @Test
    void createCustomerOrderTest() {
        var input = new CustomerOrderInput();
        // TODO fill input data
        // input.setOrderTime(Instant.now());
        // input.setStatus(OrderStatus.values()[0]);
        // input.setCustomerDetails(new Customer());
        // input.setRestaurantDetails(new Restaurant());
        // input.setOrderItems(new OrderItemInput());
        var customerOrder = ordersService.createCustomerOrder(input);
        assertNotNull(customerOrder.getId());
        assertTrue(customerOrderRepository.containsEntity(customerOrder));
    }

    @Test
    void updateOrderTest() { // TODO: implement this test
    }

    @Test
    void updateKitchenStatusTest() { // TODO: implement this test
    }

    @Test
    void updateDeliveryStatusTest() { // TODO: implement this test
    }

    @Test
    void cancelOrderTest() { // TODO: implement this test
    }

    @Test
    void searchOrdersTest() { // TODO: implement this test
    }

}
