package io.zenwave360.example.customers.events;

import io.zenwave360.example.customers.model.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/** */
@lombok.Getter
@lombok.Setter
public class CustomerAddressUpdated implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    private String customerId;

    private String addressDescription;

    private Address originalAddress;

    private Address newAddress;

}
