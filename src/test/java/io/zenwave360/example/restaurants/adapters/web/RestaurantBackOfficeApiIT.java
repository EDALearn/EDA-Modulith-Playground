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
* Integration tests for the {@link RestaurantBackOfficeApi} REST controller.
*/
public class RestaurantBackOfficeApiIT extends BaseWebTestClientTest {



    /**
    * Test: createRestaurant for OK.
    */
    @Test
    public void testCreateRestaurant_201() {
        RestaurantDTO requestBody = new RestaurantDTO();
        requestBody.setId(null);
        requestBody.setVersion(null);
        requestBody.setName(null);
        requestBody.setPhone(null);
        requestBody.setAddress(new AddressDTO());
        requestBody.getAddress().setStreet("aaa");
        requestBody.getAddress().setCity("aaa");
        requestBody.getAddress().setState("aaa");
        requestBody.getAddress().setZip("aaa");

        webTestClient.method(POST).uri("/api/restaurants")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.address").isNotEmpty()
            .jsonPath("$.address.street").isNotEmpty()
            .jsonPath("$.address.city").isNotEmpty()
            .jsonPath("$.address.state").isNotEmpty()
            .jsonPath("$.address.zip").isNotEmpty();
    }

    /**
    * Test: listRestaurants for OK.
    */
    @Test
    public void testListRestaurants_200() {
        var page = "";
        var limit = "";
        var sort = "";

        webTestClient.method(GET).uri(uriBuilder -> uriBuilder.path("/api/restaurants").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort))
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
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
            .jsonPath("$.content[0].name").isNotEmpty()
            .jsonPath("$.content[0].phone").isNotEmpty()
            .jsonPath("$.content[0].address").isNotEmpty();
    }

    /**
    * Test: getRestaurant for OK.
    */
    @Test
    public void testGetRestaurant_200() {
        var restaurantId = "";

        webTestClient.method(GET).uri("/api/restaurants/{restaurantId}", restaurantId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.address").isNotEmpty()
            .jsonPath("$.address.street").isNotEmpty()
            .jsonPath("$.address.city").isNotEmpty()
            .jsonPath("$.address.state").isNotEmpty()
            .jsonPath("$.address.zip").isNotEmpty();
    }

    /**
    * Test: createMenuItem for OK.
    */
    @Test
    public void testCreateMenuItem_201() {
        MenuItemDTO requestBody = new MenuItemDTO();
        requestBody.setId(null);
        requestBody.setVersion(null);
        requestBody.setRestaurantId(null);
        requestBody.setName(null);
        requestBody.setDescription(null);
        requestBody.setPrice(null);
        var restaurantId = "";

        webTestClient.method(POST).uri("/api/restaurants/{restaurantId}/menuItems", restaurantId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.restaurantId").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.description").isNotEmpty()
            .jsonPath("$.price").isNotEmpty();
    }

    /**
    * Test: listMenuItems for OK.
    */
    @Test
    public void testListMenuItems_200() {
        var restaurantId = "";

        webTestClient.method(GET).uri("/api/restaurants/{restaurantId}/menuItems", restaurantId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody();
    }

    /**
    * Test: updateMenuItem for OK.
    */
    @Test
    public void testUpdateMenuItem_200() {
        MenuItemDTO requestBody = new MenuItemDTO();
        requestBody.setId(null);
        requestBody.setVersion(null);
        requestBody.setRestaurantId(null);
        requestBody.setName(null);
        requestBody.setDescription(null);
        requestBody.setPrice(null);
        var restaurantId = "";
        var name = "";

        webTestClient.method(PUT).uri("/api/restaurants/{restaurantId}/menuItems/{name}", restaurantId, name)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.restaurantId").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.description").isNotEmpty()
            .jsonPath("$.price").isNotEmpty();
    }

}
