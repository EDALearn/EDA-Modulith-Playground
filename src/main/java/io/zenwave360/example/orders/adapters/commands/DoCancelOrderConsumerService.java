package io.zenwave360.example.orders.adapters.commands;

import io.zenwave360.example.orders.core.inbound.OrdersService;
import io.zenwave360.example.orders.core.inbound.dtos.CancelOrderInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
public class DoCancelOrderConsumerService {

    private OrdersService ordersService;

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Async
    @TransactionalEventListener
    public void doCancelOrder(CancelOrderInput payload) {
        log.debug("Received CancelOrderInput: {}", payload);
        var input = new io.zenwave360.example.orders.core.inbound.dtos.CancelOrderInput() //
            .setId(payload.getId())
            .setReason(payload.getReason());
        ordersService.cancelOrder(payload.getId(), input);
    }

}
