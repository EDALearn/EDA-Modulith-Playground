package io.zenwave360.example.orders.core.domain.events;

import io.zenwave360.example.orders.core.domain.*;
import jakarta.validation.constraints.*;
import org.springframework.modulith.events.Externalized;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/** */
@lombok.Getter
@lombok.Setter
// @Externalized("orders.modulith.externalized::#{#this.getId()}")
public class OrderEvent implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    private Instant orderTime;

    private OrderStatus status;

    private Customer customerDetails;

    private Restaurant restaurantDetails;

    private List<OrderItemInput> orderItems;

    private String id;

    public OrderEvent addOrderItems(OrderItemInput orderItems) {
        this.orderItems.add(orderItems);
        return this;
    }

}
