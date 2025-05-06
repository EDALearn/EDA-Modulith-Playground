package io.zenwave360.example.restaurants.core.implementation;

import io.zenwave360.example.restaurants.core.domain.KitchenOrder;
import io.zenwave360.example.restaurants.core.domain.KitchenOrderStatus;
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
        log.debug("Request to save KitchenOrder: {}", input);
        var kitchenOrder = restaurantOrdersServiceMapper.update(new KitchenOrder(), input);
        kitchenOrder.setStatus(KitchenOrderStatus.ACCEPTED);
        kitchenOrder = kitchenOrderRepository.save(kitchenOrder);
        var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                .setKitchenOrderId(kitchenOrder.getId())
                .setCustomerOrderId(input.getOrderId())
                .setStatus(KitchenOrderStatus.ACCEPTED);
        eventPublisher.onKitchenOrderStatusUpdated(kitchenOrderUpdateStatus);
        return kitchenOrder;
    }

    @Transactional
    public void onOrderStatusUpdated(OrderStatusUpdated input) {
        log.debug("Request onOrderStatusUpdated: {}", input);
        if ("CONFIRMED".equals(input.getStatus())) {
            var kitchenOrder = kitchenOrderRepository.findByOrderId(input.getOrderId()).orElseThrow();
            kitchenOrder.setStatus(KitchenOrderStatus.IN_PROGRESS);
            kitchenOrderRepository.save(kitchenOrder);
            var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                    .setKitchenOrderId(kitchenOrder.getId())
                    .setCustomerOrderId(input.getOrderId())
                    .setStatus(KitchenOrderStatus.IN_PROGRESS);
            eventPublisher.onKitchenOrderStatusUpdated(kitchenOrderUpdateStatus);
        }
        if ("CANCELLED".equals(input.getStatus())) {
            var kitchenOrder = kitchenOrderRepository.findByOrderId(input.getOrderId()).orElseThrow();
            kitchenOrder.setStatus(KitchenOrderStatus.CANCELLED);
            var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                    .setKitchenOrderId(kitchenOrder.getId())
                    .setCustomerOrderId(input.getOrderId())
                    .setStatus(KitchenOrderStatus.CANCELLED);
            eventPublisher.onKitchenOrderStatusUpdated(kitchenOrderUpdateStatus);
        }
    }

    public KitchenOrder updateKitchenOrderStatus(String id, KitchenOrderStatusInput input) {
        log.debug("Request updateKitchenOrderStatus: {}", id);
        var kitchenOrder = kitchenOrderRepository.findById(id).orElseThrow();
        kitchenOrder = restaurantOrdersServiceMapper.update(kitchenOrder, input);
        var kitchenOrderUpdateStatus = new KitchenOrderStatusUpdated() //
                .setKitchenOrderId(kitchenOrder.getId())
                .setCustomerOrderId(kitchenOrder.getOrderId())
                .setStatus(kitchenOrder.getStatus());
        kitchenOrder = kitchenOrderRepository.save(kitchenOrder);
        eventPublisher.onKitchenOrderStatusUpdated(kitchenOrderUpdateStatus);
        return kitchenOrder;
    }

    public Page<KitchenOrder> searchKitchenOrders(KitchenOrdersFilter input, Pageable pageable) {
        log.debug("Request to search KitchenOrders: {} - {}", input, pageable);
        // TODO implement this search by criteria
        var page = kitchenOrderRepository.findAll(pageable);
        return page;
    }

}
