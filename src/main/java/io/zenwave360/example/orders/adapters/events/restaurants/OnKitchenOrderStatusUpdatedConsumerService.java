package io.zenwave360.example.orders.adapters.events.restaurants;

import io.zenwave360.example.orders.core.inbound.OrdersService;
import io.zenwave360.example.orders.core.inbound.dtos.KitchenStatus;
import io.zenwave360.example.orders.core.inbound.dtos.KitchenStatusInput;
import io.zenwave360.example.restaurants.core.domain.KitchenOrderStatus;
import io.zenwave360.example.restaurants.core.domain.events.KitchenOrderStatusUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnKitchenOrderStatusUpdatedConsumerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OrdersService ordersService;

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /** */
    @Async
    @TransactionalEventListener
    public void onKitchenOrderStatusUpdated(KitchenOrderStatusUpdated payload) {
        log.debug("Received command request for onKitchenOrderStatusUpdated: {}", payload);
        KitchenStatusInput input = new KitchenStatusInput() //
                .setKitchenOrderId(payload.getKitchenOrderId())
                .setKitchenStatus(map(payload.getStatus()));
        ordersService.updateKitchenStatus(payload.getCustomerOrderId(), input);
    };

    private KitchenStatus map(KitchenOrderStatus status) {
        return KitchenStatus.valueOf(status.name());
    }
}
