package io.zenwave360.example.restaurants.adapters.web;

import io.zenwave360.example.restaurants.adapters.web.dtos.*;
import io.zenwave360.example.restaurants.adapters.web.mappers.*;
import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.inbound.*;
import io.zenwave360.example.restaurants.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/** REST controller for RestaurantOrdersApi. */
@RestController
@RequestMapping("/api")
public class RestaurantOrdersApiController implements RestaurantOrdersApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private RestaurantOrdersService restaurantOrdersService;

    @Autowired
    public RestaurantOrdersApiController setRestaurantOrdersService(RestaurantOrdersService restaurantOrdersService) {
        this.restaurantOrdersService = restaurantOrdersService;
        return this;
    }

    private RestaurantOrdersDTOsMapper mapper = RestaurantOrdersDTOsMapper.INSTANCE;

    public RestaurantOrdersApiController(RestaurantOrdersService restaurantOrdersService) {

        this.restaurantOrdersService = restaurantOrdersService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<KitchenOrderDTO> updateKitchenOrderStatus(String orderId,
            KitchenOrderStatusInputDTO reqBody) {
        var input = mapper.asKitchenOrderStatusInput(reqBody);
        var kitchenOrder = restaurantOrdersService.updateKitchenOrderStatus(orderId, input);
        KitchenOrderDTO responseDTO = mapper.asKitchenOrderDTO(kitchenOrder);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<KitchenOrderPaginatedDTO> searchKitchenOrders(Integer page, Integer limit,
            List<String> sort, KitchenOrdersFilterDTO reqBody) {
        var input = mapper.asKitchenOrdersFilter(reqBody);
        var kitchenOrderPage = restaurantOrdersService.searchKitchenOrders(input, pageOf(page, limit, sort));
        var responseDTO = mapper.asKitchenOrderPaginatedDTO(kitchenOrderPage);
        return ResponseEntity.status(201).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null ? Sort.by(sort.stream().map(sortParam -> {
            String[] parts = sortParam.split(":");
            String property = parts[0];
            Sort.Direction direction = parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
            return new Sort.Order(direction, property);
        }).toList()) : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
