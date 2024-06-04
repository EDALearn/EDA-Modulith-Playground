package io.zenwave360.example.restaurants.adapters.web;

import io.zenwave360.example.restaurants.adapters.web.*;
import io.zenwave360.example.restaurants.adapters.web.dtos.*;
import io.zenwave360.example.restaurants.adapters.web.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.*;

/**
* Integration tests for the {@link RestaurantOrdersApi} REST controller.
*/
public class RestaurantOrdersApiIT extends BaseWebTestClientTest {



    /**
    * Test: updateKitchenOrderStatus for OK.
    */
    @Test
    public void testUpdateKitchenOrderStatus_200() {
        KitchenOrderStatusInputDTO requestBody = new KitchenOrderStatusInputDTO();
        requestBody.setStatus(null);
        requestBody.setOperatorName(null);
        var orderId = "";

        webTestClient.method(PUT).uri("/api/restaurants-orders/{orderId}/status", orderId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.orderId").isNotEmpty()
            .jsonPath("$.restaurantId").isNotEmpty()
            .jsonPath("$.date").isNotEmpty()
            .jsonPath("$.items").isNotEmpty()
            .jsonPath("$.items").isArray()
            .jsonPath("$.items[0].id").isNotEmpty()
            .jsonPath("$.items[0].version").isNotEmpty()
            .jsonPath("$.items[0].restaurantId").isNotEmpty()
            .jsonPath("$.items[0].name").isNotEmpty()
            .jsonPath("$.items[0].description").isNotEmpty()
            .jsonPath("$.items[0].price").isNotEmpty()
            .jsonPath("$.status").isNotEmpty()
            .jsonPath("$.customer").isNotEmpty()
            .jsonPath("$.customer.name").isNotEmpty()
            .jsonPath("$.customer.phone").isNotEmpty()
            .jsonPath("$.customer.address").isNotEmpty();
    }

    /**
    * Test: searchKitchenOrders for OK.
    */
    @Test
    public void testSearchKitchenOrders_201() {
        KitchenOrdersFilterDTO requestBody = new KitchenOrdersFilterDTO();
        requestBody.setRestaurantId(null);
        requestBody.setStatus(null);
        var page = "";
        var limit = "";
        var sort = "";

        webTestClient.method(POST).uri(uriBuilder -> uriBuilder.path("/api/restaurants-orders").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.number").isNotEmpty()
            .jsonPath("$.numberOfElements").isNotEmpty()
            .jsonPath("$.size").isNotEmpty()
            .jsonPath("$.totalElements").isNotEmpty()
            .jsonPath("$.totalPages").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
            .jsonPath("$.content").isArray()
            .jsonPath("$.content[0].id").isNotEmpty()
            .jsonPath("$.content[0].version").isNotEmpty()
            .jsonPath("$.content[0].orderId").isNotEmpty()
            .jsonPath("$.content[0].restaurantId").isNotEmpty()
            .jsonPath("$.content[0].date").isNotEmpty()
            .jsonPath("$.content[0].items").isNotEmpty()
            .jsonPath("$.content[0].status").isNotEmpty()
            .jsonPath("$.content[0].customer").isNotEmpty();
    }

}
