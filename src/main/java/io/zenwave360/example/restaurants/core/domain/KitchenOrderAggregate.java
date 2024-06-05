package io.zenwave360.example.restaurants.core.domain;

import io.zenwave360.example.restaurants.core.domain.events.*;
import io.zenwave360.example.restaurants.core.inbound.dtos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

public class KitchenOrderAggregate {

    private static final Mapper mapper = Mappers.getMapper(Mapper.class);

    private final KitchenOrder rootEntity;

    private final List<Object> events = new ArrayList<>();

    public KitchenOrderAggregate() {
        this(new KitchenOrder());
    }

    public KitchenOrderAggregate(KitchenOrder rootEntity) {
        this.rootEntity = rootEntity;
    }

    public String getId() {
        return rootEntity.getId();
    }

    public KitchenOrder getRootEntity() {
        return rootEntity;
    }

    public List<?> getEvents() {
        return Collections.unmodifiableList(events);
    }

    /** With Events: [KitchenOrderStatusUpdated]. */
    public void createKitchenOrder(KitchenOrderInput input) {
        mapper.update(rootEntity, input);
        rootEntity.setId(UUID.randomUUID().toString()); // XXX: User Assigned ID necessary for sending events
        rootEntity.setStatus(KitchenOrderStatus.ACCEPTED);
        var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                .setKitchenOrderId(rootEntity.getId())
                .setCustomerOrderId(input.getOrderId())
                .setStatus(KitchenOrderStatus.ACCEPTED);
        events.add(kitchenOrderUpdateStatus);
    }

    /** With Events: [KitchenOrderStatusUpdated]. */
    public void onOrderStatusUpdated(OrderStatusUpdated input) {
        var kitchenOrder = rootEntity;
        if ("CONFIRMED".equals(input.getStatus())) {
            kitchenOrder.setStatus(KitchenOrderStatus.IN_PROGRESS);
            var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                    .setKitchenOrderId(kitchenOrder.getId())
                    .setCustomerOrderId(input.getOrderId())
                    .setStatus(KitchenOrderStatus.IN_PROGRESS);
            events.add(kitchenOrderUpdateStatus);
        }
        if ("CANCELLED".equals(input.getStatus())) {
            kitchenOrder.setStatus(KitchenOrderStatus.CANCELLED);
            var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                    .setKitchenOrderId(kitchenOrder.getId())
                    .setCustomerOrderId(input.getOrderId())
                    .setStatus(KitchenOrderStatus.CANCELLED);
            events.add(kitchenOrderUpdateStatus);
        }
    }

    /** With Events: [KitchenOrderStatusUpdated]. */
    public void updateKitchenOrderStatus(KitchenOrderStatusInput input) {
        mapper.update(rootEntity, input);
        var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                .setKitchenOrderId(rootEntity.getId())
                .setCustomerOrderId(rootEntity.getOrderId())
                .setStatus(rootEntity.getStatus());
        events.add(kitchenOrderUpdateStatus);
    }

    @org.mapstruct.Mapper
    interface Mapper {

        KitchenOrder update(@MappingTarget KitchenOrder entity, OrderStatusUpdated input);

        KitchenOrder update(@MappingTarget KitchenOrder entity, KitchenOrderStatusInput input);

        KitchenOrder update(@MappingTarget KitchenOrder entity, KitchenOrderInput input);

    }

}
