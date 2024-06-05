package io.zenwave360.example.orders.core.implementation.mappers;

import io.zenwave360.example.customers.dtos.AddressDTO;
import io.zenwave360.example.customers.dtos.CustomerDTO;
import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.inbound.dtos.*;
import io.zenwave360.example.restaurants.adapters.web.dtos.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { BaseMapper.class })
public interface OrdersServiceMapper {

    OrdersServiceMapper INSTANCE = Mappers.getMapper(OrdersServiceMapper.class);

    // CustomerOrder asCustomerOrder(OrdersFilter input);

    @Mapping(target = "id", ignore = true)
    CustomerOrder update(@MappingTarget CustomerOrder entity, OrdersFilter input);
    // CustomerOrder asCustomerOrder(DeliveryStatusInput input);

    @Mapping(target = "id", ignore = true)
    CustomerOrder update(@MappingTarget CustomerOrder entity, DeliveryStatusInput input);
    // CustomerOrder asCustomerOrder(KitchenStatusInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "customerDetails", source = "customerDetails")
    @Mapping(target = "customerDetails.address", source = "address")
    CustomerOrder update(@MappingTarget CustomerOrder entity, CustomerOrderInput input, CustomerDTO customerDetails, AddressDTO address, RestaurantDTO restaurantDetails);


    @Mapping(target = "id", ignore = true)
    CustomerOrder update(@MappingTarget CustomerOrder entity, KitchenStatusInput input);
    // CustomerOrder asCustomerOrder(CustomerOrderInput input);

    @Mapping(target = "id", ignore = true)
    CustomerOrder update(@MappingTarget CustomerOrder entity, CustomerOrderInput input);
    // CustomerOrder asCustomerOrder(CancelOrderInput input);

    @Mapping(target = "id", ignore = true)
    CustomerOrder update(@MappingTarget CustomerOrder entity, CancelOrderInput input);

}
