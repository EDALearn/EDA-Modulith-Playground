package io.zenwave360.example.orders.infrastructure.events;

import io.zenwave360.example.orders.core.domain.events.OrderEvent;
import io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated;
import io.zenwave360.example.orders.core.outbound.events.OrdersEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@lombok.AllArgsConstructor
@Slf4j
public class EventExternalizer {

//    private OrdersEventsProducer ordersEventsProducer;
//    private final EventsMapper eventsMapper = Mappers.getMapper(EventsMapper.class);
//
//    @ApplicationModuleListener
//    public void onOrderEvent(OrderEvent event) {
//        log.debug("Externalizing OrderEvent: {}", event);
//        ordersEventsProducer.onOrderEvent(eventsMapper.asOrderEvent(event));
//    }
//
//    @ApplicationModuleListener
//    public void onOrderStatusUpdated(OrderStatusUpdated event) {
//        log.debug("Externalizing OrderStatusUpdated: {}", event);
//        ordersEventsProducer.onOrderStatusUpdated(eventsMapper.asOrderStatusUpdated(event));
//    }
//
//    @Mapper
//    interface EventsMapper {
//        io.zenwave360.example.orders.core.domain.events2.OrderEvent asOrderEvent(OrderEvent event);
//
//        io.zenwave360.example.orders.core.domain.events2.OrderStatusUpdated asOrderStatusUpdated(OrderStatusUpdated event);
//    }
}
