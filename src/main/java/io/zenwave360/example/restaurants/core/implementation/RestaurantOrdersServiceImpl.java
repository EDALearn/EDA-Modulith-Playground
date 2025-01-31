package io.zenwave360.example.restaurants.core.implementation;

import io.zenwave360.example.restaurants.core.domain.KitchenOrder;
import io.zenwave360.example.restaurants.core.domain.KitchenOrderAggregate;
import io.zenwave360.example.restaurants.core.domain.events.KitchenOrderStatusUpdated;
import io.zenwave360.example.restaurants.core.implementation.mappers.EventsMapper;
import io.zenwave360.example.restaurants.core.implementation.mappers.RestaurantOrdersServiceMapper;
import io.zenwave360.example.restaurants.core.inbound.RestaurantOrdersService;
import io.zenwave360.example.restaurants.core.inbound.dtos.KitchenOrderInput;
import io.zenwave360.example.restaurants.core.inbound.dtos.KitchenOrderStatusInput;
import io.zenwave360.example.restaurants.core.inbound.dtos.KitchenOrdersFilter;
import io.zenwave360.example.restaurants.core.inbound.dtos.OrderStatusUpdated;
import io.zenwave360.example.restaurants.core.outbound.events.EventPublisher;
import io.zenwave360.example.restaurants.core.outbound.mongodb.KitchenOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing [KitchenOrder]. */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class RestaurantOrdersServiceImpl implements RestaurantOrdersService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantOrdersServiceMapper restaurantOrdersServiceMapper = RestaurantOrdersServiceMapper.INSTANCE;

    private final KitchenOrderRepository kitchenOrderRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;

    private final EventPublisher eventPublisher;

    @Transactional
    public KitchenOrder createKitchenOrder(KitchenOrderInput input) {
        log.debug("Request createKitchenOrder: {}", input);
        var kitchenOrderAggregate = new KitchenOrderAggregate();
        kitchenOrderAggregate.createKitchenOrder(input);
        persistAndEmitEvents(kitchenOrderAggregate);
        return kitchenOrderAggregate.getRootEntity();
    }

    public void onOrderStatusUpdated(OrderStatusUpdated input) {
        log.debug("Request onOrderStatusUpdated: {}", input);
        var kitchenOrderAggregate = new KitchenOrderAggregate(kitchenOrderRepository.findByOrderId(input.getOrderId()).orElseThrow());
        kitchenOrderAggregate.onOrderStatusUpdated(input);
        persistAndEmitEvents(kitchenOrderAggregate);
    }

    @Transactional
    public KitchenOrder updateKitchenOrderStatus(String id, KitchenOrderStatusInput input) {
        log.debug("Request updateKitchenOrderStatus: {} {}", id, input);
        var kitchenOrderAggregate = kitchenOrderRepository.findKitchenOrderAggregateById(id).orElseThrow();
        kitchenOrderAggregate.updateKitchenOrderStatus(input);
        persistAndEmitEvents(kitchenOrderAggregate);
        return kitchenOrderAggregate.getRootEntity();
    }

    public Page<KitchenOrder> searchKitchenOrders(KitchenOrdersFilter input, Pageable pageable) {
        log.debug("Request searchKitchenOrders: {} {}", input, pageable);

        var kitchenOrders = kitchenOrderRepository.findAll(pageable);
        return kitchenOrders;
    }

    private KitchenOrderAggregate persistAndEmitEvents(KitchenOrderAggregate kitchenOrderAggregate) {
        var kitchenOrder = kitchenOrderRepository.save(kitchenOrderAggregate.getRootEntity());
        kitchenOrderAggregate.getEvents().forEach(event -> {
            if (event instanceof KitchenOrderStatusUpdated) {
                eventPublisher.onKitchenOrderStatusUpdated((KitchenOrderStatusUpdated) event);
            }
        });
        return kitchenOrderAggregate;
    }

}
