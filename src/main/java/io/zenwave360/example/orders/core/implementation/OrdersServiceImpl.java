package io.zenwave360.example.orders.core.implementation;

import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.domain.events.*;
import io.zenwave360.example.orders.core.implementation.mappers.*;
import io.zenwave360.example.orders.core.inbound.*;
import io.zenwave360.example.orders.core.inbound.dtos.*;
import io.zenwave360.example.orders.core.outbound.mongodb.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing [CustomerOrder]. */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrdersServiceMapper ordersServiceMapper = OrdersServiceMapper.INSTANCE;

    private final CustomerOrderRepository customerOrderRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Optional<CustomerOrder> getCustomerOrder(String id) {
        log.debug("[CRUD] Request to get CustomerOrder : {}", id);
        var customerOrder = customerOrderRepository.findById(id);
        return customerOrder;
    }

    @Transactional
    public CustomerOrder createCustomerOrder(CustomerOrderInput input) {
        log.debug("[CRUD] Request to save CustomerOrder: {}", input);
        var customerOrder = ordersServiceMapper.update(new CustomerOrder(), input);
        customerOrder = customerOrderRepository.save(customerOrder);
        // emit events
        var orderEvent = eventsMapper.asOrderEvent(customerOrder);
        applicationEventPublisher.publishEvent(orderEvent);
        return customerOrder;
    }

    @Transactional
    public CustomerOrder updateOrder(String id, CustomerOrderInput input) {
        log.debug("Request updateOrder: {} {}", id, input);

        var customerOrder = customerOrderRepository.findById(id).map(existingCustomerOrder -> {
            return ordersServiceMapper.update(existingCustomerOrder, input);
        }).map(customerOrderRepository::save).orElseThrow();
        // emit events
        var orderEvent = eventsMapper.asOrderEvent(customerOrder);
        applicationEventPublisher.publishEvent(orderEvent);
        var orderStatusUpdated = eventsMapper.asOrderStatusUpdated(customerOrder);
        applicationEventPublisher.publishEvent(orderStatusUpdated);
        return customerOrder;
    }

    @Transactional
    public CustomerOrder updateKitchenStatus(String id, KitchenStatusInput input) {
        log.debug("Request updateKitchenStatus: {} {}", id, input);

        var customerOrder = customerOrderRepository.findById(id).map(existingCustomerOrder -> {
            return ordersServiceMapper.update(existingCustomerOrder, input);
        }).map(customerOrderRepository::save).orElseThrow();
        // emit events
        var orderEvent = eventsMapper.asOrderEvent(customerOrder);
        applicationEventPublisher.publishEvent(orderEvent);
        var orderStatusUpdated = eventsMapper.asOrderStatusUpdated(customerOrder);
        applicationEventPublisher.publishEvent(orderStatusUpdated);
        return customerOrder;
    }

    @Transactional
    public CustomerOrder updateDeliveryStatus(String id, DeliveryStatusInput input) {
        log.debug("Request updateDeliveryStatus: {} {}", id, input);

        var customerOrder = customerOrderRepository.findById(id).map(existingCustomerOrder -> {
            return ordersServiceMapper.update(existingCustomerOrder, input);
        }).map(customerOrderRepository::save).orElseThrow();
        // emit events
        var orderEvent = eventsMapper.asOrderEvent(customerOrder);
        applicationEventPublisher.publishEvent(orderEvent);
        var orderStatusUpdated = eventsMapper.asOrderStatusUpdated(customerOrder);
        applicationEventPublisher.publishEvent(orderStatusUpdated);
        return customerOrder;
    }

    @Transactional
    public CustomerOrder cancelOrder(String id, CancelOrderInput input) {
        log.debug("Request cancelOrder: {} {}", id, input);

        var customerOrder = customerOrderRepository.findById(id).map(existingCustomerOrder -> {
            return ordersServiceMapper.update(existingCustomerOrder, input);
        }).map(customerOrderRepository::save).orElseThrow();
        // emit events
        var orderEvent = eventsMapper.asOrderEvent(customerOrder);
        applicationEventPublisher.publishEvent(orderEvent);
        var orderStatusUpdated = eventsMapper.asOrderStatusUpdated(customerOrder);
        applicationEventPublisher.publishEvent(orderStatusUpdated);
        return customerOrder;
    }

    public List<CustomerOrder> searchOrders(OrdersFilter input) {
        log.debug("Request searchOrders: {}", input);

        var customerOrders = customerOrderRepository.findAll();
        return customerOrders;
    }

}
