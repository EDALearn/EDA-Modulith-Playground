package io.zenwave360.example.restaurants.core.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.restaurants.config.*;
import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.implementation.mappers.*;
import io.zenwave360.example.restaurants.core.inbound.*;
import io.zenwave360.example.restaurants.core.inbound.dtos.*;
import io.zenwave360.example.restaurants.core.outbound.mongodb.*;
import io.zenwave360.example.restaurants.infrastructure.mongodb.inmemory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Acceptance Test for RestaurantBackOfficeService. */
public class RestaurantBackOfficeServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    RestaurantBackOfficeServiceImpl restaurantBackOfficeService = context.restaurantBackOfficeService();

    RestaurantRepositoryInMemory restaurantRepository = context.restaurantRepository();

    MenuItemRepositoryInMemory menuItemRepository = context.menuItemRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void createRestaurantTest() {
        var input = new Restaurant();
        // TODO fill input data
        // input.setName("aa");
        // input.setPhone("");
        // input.setAddress(new Address());
        var restaurant = restaurantBackOfficeService.createRestaurant(input);
        assertNotNull(restaurant.getId());
        assertTrue(restaurantRepository.containsEntity(restaurant)); // TODO: implement
                                                                     // this test
    }

    @Test
    void getRestaurantTest() {
        var id = "1"; // TODO fill id
        var restaurant = restaurantBackOfficeService.getRestaurant(id);
        assertTrue(restaurant.isPresent()); // TODO: implement this test
    }

    @Test
    void listRestaurantsTest() {
        // var results = restaurantBackOfficeService.listRestaurants(PageRequest.of(0,
        // 10));
        // assertNotNull(results);// TODO: implement this test
    }

    @Test
    void createMenuItemTest() { // TODO: implement this test
        var input = new MenuItem();
        // TODO fill input data
        // input.setRestaurantId("");
        // input.setName("aa");
        // input.setDescription("");
        // input.setPrice(BigDecimal.valueOf(0));
        var menuItem = restaurantBackOfficeService.createMenuItem(input);
        assertNotNull(menuItem.getId());
        assertTrue(menuItemRepository.containsEntity(menuItem));
    }

    @Test
    void updateMenuItemTest() { // TODO: implement this test
        var id = "1"; // TODO fill id
        var input = new MenuItem();
        // TODO fill input data
        // input.setRestaurantId("");
        // input.setName("aa");
        // input.setDescription("");
        // input.setPrice(BigDecimal.valueOf(0));
        assertTrue(menuItemRepository.containsKey(id));
        var menuItem = restaurantBackOfficeService.updateMenuItem(id, input);
        assertTrue(menuItem.isPresent());
        assertTrue(menuItemRepository.containsEntity(menuItem.get()));
    }

    @Test
    void listMenuItemsTest() { // TODO: implement this test
        // var results = restaurantBackOfficeService.listMenuItems(PageRequest.of(0, 10));
        // assertNotNull(results);
    }

}
