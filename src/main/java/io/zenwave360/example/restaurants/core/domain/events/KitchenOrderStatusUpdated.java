package io.zenwave360.example.restaurants.core.domain.events;

import io.zenwave360.example.restaurants.core.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/** */
@lombok.Getter
@lombok.Setter
public class KitchenOrderStatusUpdated implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private String customerOrderId;

    @NotNull
    private String kitchenOrderId;

    @NotNull
    private KitchenOrderStatus status;

    private String message;

}
