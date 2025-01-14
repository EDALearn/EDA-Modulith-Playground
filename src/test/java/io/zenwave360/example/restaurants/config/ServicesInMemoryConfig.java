package io.zenwave360.example.restaurants.config;

import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.domain.events.*;
import io.zenwave360.example.restaurants.core.implementation.*;
import io.zenwave360.example.restaurants.core.inbound.*;
import io.zenwave360.example.restaurants.infrastructure.events.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    private InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();

    protected final RestaurantBackOfficeServiceImpl restaurantBackOfficeService = new RestaurantBackOfficeServiceImpl(
            restaurantRepository(), menuItemRepository(), eventPublisher);

    protected final RestaurantOrdersServiceImpl restaurantOrdersService = new RestaurantOrdersServiceImpl(
            kitchenOrderRepository(), eventPublisher);

    @Bean
    public <T extends RestaurantBackOfficeService> T restaurantBackOfficeService() {
        return (T) restaurantBackOfficeService;
    }

    @Bean
    public <T extends RestaurantOrdersService> T restaurantOrdersService() {
        return (T) restaurantOrdersService;
    }

    static List<Restaurant> _restaurants;
    static List<MenuItem> _menuItems;
    static List<KitchenOrder> _kitchenOrders;

    public void reloadTestData() {
        var testDataLoader = new TestDataLoader(List.of(Restaurant.class, Address.class, MenuItem.class,
                KitchenOrder.class, CustomerDetails.class, CustomerAddress.class));
        var restaurants = _restaurants != null ? _restaurants
                : testDataLoader.loadCollectionTestDataAsObjects(Restaurant.class);
        restaurantRepository().deleteAll();
        restaurantRepository().saveAll(restaurants);
        var menuItems = _menuItems != null ? _menuItems
                : testDataLoader.loadCollectionTestDataAsObjects(MenuItem.class);
        menuItemRepository().deleteAll();
        menuItemRepository().saveAll(menuItems);
        var kitchenOrders = _kitchenOrders != null ? _kitchenOrders
                : testDataLoader.loadCollectionTestDataAsObjects(KitchenOrder.class);
        kitchenOrderRepository().deleteAll();
        kitchenOrderRepository().saveAll(kitchenOrders);
        eventPublisher.getEvents().clear();
    }

    @Bean
    public InMemoryEventPublisher eventPublisher() {
        return eventPublisher;
    }

}
