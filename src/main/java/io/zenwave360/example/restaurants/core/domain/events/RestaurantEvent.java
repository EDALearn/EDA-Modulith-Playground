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
public class RestaurantEvent implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 2, max = 250)
    private String name;

    @NotNull
    private String phone;

    private Address address;

    @NotNull
    private String id;

}
