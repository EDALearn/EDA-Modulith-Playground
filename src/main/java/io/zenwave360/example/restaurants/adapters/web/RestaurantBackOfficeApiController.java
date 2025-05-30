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

/** REST controller for RestaurantBackOfficeApi. */
@RestController
@RequestMapping("/api")
public class RestaurantBackOfficeApiController implements RestaurantBackOfficeApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private RestaurantBackOfficeService restaurantBackOfficeService;

    @Autowired
    public RestaurantBackOfficeApiController setRestaurantBackOfficeService(
            RestaurantBackOfficeService restaurantBackOfficeService) {
        this.restaurantBackOfficeService = restaurantBackOfficeService;
        return this;
    }

    private RestaurantBackOfficeDTOsMapper mapper = RestaurantBackOfficeDTOsMapper.INSTANCE;

    public RestaurantBackOfficeApiController(RestaurantBackOfficeService restaurantBackOfficeService) {

        this.restaurantBackOfficeService = restaurantBackOfficeService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<RestaurantDTO> createRestaurant(RestaurantDTO reqBody) {
        var input = mapper.asRestaurant(reqBody);
        var restaurant = restaurantBackOfficeService.createRestaurant(input);
        RestaurantDTO responseDTO = mapper.asRestaurantDTO(restaurant);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<RestaurantPaginatedDTO> listRestaurants(Integer page, Integer limit,
            List<String> sort) {
        var restaurantPage = restaurantBackOfficeService.listRestaurants(pageOf(page, limit, sort));
        var responseDTO = mapper.asRestaurantPaginatedDTO(restaurantPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<RestaurantDTO> getRestaurant(String restaurantId) {
        var restaurant = restaurantBackOfficeService.getRestaurant(restaurantId);
        if (restaurant.isPresent()) {
            RestaurantDTO responseDTO = mapper.asRestaurantDTO(restaurant.get());
            return ResponseEntity.status(200).body(responseDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<MenuItemDTO> createMenuItem(String restaurantId, MenuItemDTO reqBody) {
        var input = mapper.asMenuItem(reqBody);
        var menuItem = restaurantBackOfficeService.createMenuItem(input);
        MenuItemDTO responseDTO = mapper.asMenuItemDTO(menuItem);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<MenuItemDTO>> listMenuItems(String restaurantId) {
        var menuItem = restaurantBackOfficeService.listMenuItems(restaurantId);
        var responseDTO = mapper.asMenuItemDTOList(menuItem);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<MenuItemDTO> updateMenuItem(String restaurantId, String name, MenuItemDTO reqBody) {
        var input = mapper.asMenuItem(reqBody);
        var menuItem = restaurantBackOfficeService.updateMenuItem(restaurantId, input);
        if (menuItem.isPresent()) {
            MenuItemDTO responseDTO = mapper.asMenuItemDTO(menuItem.get());
            return ResponseEntity.status(200).body(responseDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
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
