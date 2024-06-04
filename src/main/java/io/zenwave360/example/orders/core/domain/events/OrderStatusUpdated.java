package io.zenwave360.example.orders.core.domain.events;

import io.zenwave360.example.orders.core.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/** */
@lombok.Getter
@lombok.Setter
public class OrderStatusUpdated implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private Instant dateTime;

    private OrderStatus status;

    private OrderStatus previousStatus;

}
