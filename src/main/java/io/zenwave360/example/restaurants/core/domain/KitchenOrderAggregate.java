package io.zenwave360.example.restaurants.core.domain;

import io.zenwave360.example.restaurants.core.domain.events.*;
import io.zenwave360.example.restaurants.core.inbound.dtos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        // TODO: implement this command
        mapper.update(rootEntity, input);
        events.add(mapper.asKitchenOrderStatusUpdated(rootEntity));
    }

    /** With Events: [KitchenOrderStatusUpdated]. */
    public void onOrderStatusUpdated(OrderStatusUpdated input) {
        // TODO: implement this command
        mapper.update(rootEntity, input);
        events.add(mapper.asKitchenOrderStatusUpdated(rootEntity));
    }

    /** With Events: [KitchenOrderStatusUpdated]. */
    public void updateKitchenOrderStatus(KitchenOrderStatusInput input) {
        // TODO: implement this command
        mapper.update(rootEntity, input);
        events.add(mapper.asKitchenOrderStatusUpdated(rootEntity));
    }

    @org.mapstruct.Mapper
    interface Mapper {

        KitchenOrder update(@MappingTarget KitchenOrder entity, OrderStatusUpdated input);

        KitchenOrder update(@MappingTarget KitchenOrder entity, KitchenOrderStatusInput input);

        KitchenOrder update(@MappingTarget KitchenOrder entity, KitchenOrderInput input);

        KitchenOrderStatusUpdated asKitchenOrderStatusUpdated(KitchenOrder entity);

    }

}
