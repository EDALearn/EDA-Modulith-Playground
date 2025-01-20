package io.zenwave360.example.delivery.core.implementation;

import io.zenwave360.example.delivery.core.domain.Delivery;
import io.zenwave360.example.delivery.core.domain.DeliveryAggregate;
import io.zenwave360.example.delivery.core.domain.events.DeliveryStatusUpdated;
import io.zenwave360.example.delivery.core.implementation.mappers.DeliveryServiceMapper;
import io.zenwave360.example.delivery.core.implementation.mappers.EventsMapper;
import io.zenwave360.example.delivery.core.inbound.DeliveryService;
import io.zenwave360.example.delivery.core.inbound.dtos.DeliveryInput;
import io.zenwave360.example.delivery.core.inbound.dtos.DeliveryStatusInput;
import io.zenwave360.example.delivery.core.inbound.dtos.OrderStatusUpdated;
import io.zenwave360.example.delivery.core.outbound.events.EventPublisher;
import io.zenwave360.example.delivery.core.outbound.mongodb.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing [Delivery]. */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DeliveryServiceMapper deliveryServiceMapper = DeliveryServiceMapper.INSTANCE;

    private final DeliveryRepository deliveryRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;

    private final EventPublisher eventPublisher;

    @Transactional
    public Delivery createDelivery(DeliveryInput input) {
        log.debug("Request createDelivery: {}", input);
        var deliveryAggregate = new DeliveryAggregate();
        deliveryAggregate.createDelivery(input);
        persistAndEmitEvents(deliveryAggregate);
        return deliveryAggregate.getRootEntity();
    }

    public Delivery onOrderStatusUpdated(OrderStatusUpdated input) {
        log.debug("Request onOrderStatusUpdated: {}", input);
        var deliveryAggregate = new DeliveryAggregate(deliveryRepository.findByOrderId(input.getOrderId()).orElseThrow());
        deliveryAggregate.onOrderStatusUpdated(input);
        persistAndEmitEvents(deliveryAggregate);
        return deliveryAggregate.getRootEntity();
    }

    @Transactional
    public Delivery updateDeliveryStatus(String id, DeliveryStatusInput input) {
        log.debug("Request updateDeliveryStatus: {} {}", id, input);
        var deliveryAggregate = deliveryRepository.findDeliveryAggregateById(id).orElseThrow();
        deliveryAggregate.updateDeliveryStatus(input);
        persistAndEmitEvents(deliveryAggregate);
        return deliveryAggregate.getRootEntity();
    }

    public Page<Delivery> listDeliveries(Pageable pageable) {
        log.debug("Request listDeliveries: {}", pageable);

        var deliveries = deliveryRepository.findAll(pageable);
        return deliveries;
    }

    private DeliveryAggregate persistAndEmitEvents(DeliveryAggregate deliveryAggregate) {
        var delivery = deliveryRepository.save(deliveryAggregate.getRootEntity());
        deliveryAggregate.getEvents().forEach(event -> {
            if (event instanceof DeliveryStatusUpdated) {
                eventPublisher.onDeliveryStatusUpdated((DeliveryStatusUpdated) event);
            }
        });
        return deliveryAggregate;
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
