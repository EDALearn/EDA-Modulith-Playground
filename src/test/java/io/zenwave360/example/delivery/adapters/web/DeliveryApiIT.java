package io.zenwave360.example.delivery.adapters.web;

import io.zenwave360.example.delivery.adapters.web.*;
import io.zenwave360.example.delivery.adapters.web.dtos.*;
import io.zenwave360.example.delivery.adapters.web.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.*;

/**
* Integration tests for the {@link DeliveryApi} REST controller.
*/
public class DeliveryApiIT extends BaseWebTestClientTest {



    /**
    * Test: updateDeliveryStatus for OK.
    */
    @Test
    public void testUpdateDeliveryStatus_200() {
        DeliveryStatusInputDTO requestBody = new DeliveryStatusInputDTO();
        requestBody.setStatus(null);
        requestBody.setOperatorName(null);
        var orderId = "";

        webTestClient.method(PUT).uri("/api/delivery/{orderId}/status", orderId)
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
            .jsonPath("$.customer").isNotEmpty()
            .jsonPath("$.customer.customerId").isNotEmpty()
            .jsonPath("$.customer.name").isNotEmpty()
            .jsonPath("$.customer.phone").isNotEmpty()
            .jsonPath("$.customer.address").isNotEmpty()
            .jsonPath("$.restaurant").isNotEmpty()
            .jsonPath("$.restaurant.restaurantId").isNotEmpty()
            .jsonPath("$.restaurant.name").isNotEmpty()
            .jsonPath("$.restaurant.phone").isNotEmpty()
            .jsonPath("$.restaurant.address").isNotEmpty()
            .jsonPath("$.orderItems").isNotEmpty()
            .jsonPath("$.orderItems").isArray()
            .jsonPath("$.orderItems[0].menuItemId").isNotEmpty()
            .jsonPath("$.orderItems[0].name").isNotEmpty()
            .jsonPath("$.orderItems[0].description").isNotEmpty()
            .jsonPath("$.orderItems[0].price").isNotEmpty()
            .jsonPath("$.orderItems[0].quantity").isNotEmpty()
            .jsonPath("$.status").isNotEmpty();
    }

    /**
    * Test: listDeliveries for OK.
    */
    @Test
    public void testListDeliveries_200() {
        var page = "";
        var limit = "";
        var sort = "";

        webTestClient.method(GET).uri(uriBuilder -> uriBuilder.path("/api/delivery").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort))
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
            .jsonPath("$.content[0].orderId").isNotEmpty()
            .jsonPath("$.content[0].customer").isNotEmpty()
            .jsonPath("$.content[0].restaurant").isNotEmpty()
            .jsonPath("$.content[0].orderItems").isNotEmpty()
            .jsonPath("$.content[0].status").isNotEmpty();
    }

}
