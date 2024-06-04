package io.zenwave360.example.orders.adapters.web;

import io.zenwave360.example.orders.adapters.web.*;
import io.zenwave360.example.orders.adapters.web.model.*;
import io.zenwave360.example.orders.adapters.web.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.*;

/**
* Integration tests for the {@link OrdersApi} REST controller.
*/
public class OrdersApiIT extends BaseWebTestClientTest {



    /**
    * Test: getCustomerOrder for OK.
    */
    @Test
    public void testGetCustomerOrder_200() {
        var orderId = "";

        webTestClient.method(GET).uri("/api/orders/{orderId}", orderId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.orderTime").isNotEmpty()
            .jsonPath("$.status").isNotEmpty()
            .jsonPath("$.customerDetails").isNotEmpty()
            .jsonPath("$.customerDetails.customerId").isNotEmpty()
            .jsonPath("$.customerDetails.firstName").isNotEmpty()
            .jsonPath("$.customerDetails.lastName").isNotEmpty()
            .jsonPath("$.customerDetails.email").isNotEmpty()
            .jsonPath("$.customerDetails.phone").isNotEmpty()
            .jsonPath("$.customerDetails.address").isNotEmpty()
            .jsonPath("$.restaurantDetails").isNotEmpty()
            .jsonPath("$.restaurantDetails.restaurantId").isNotEmpty()
            .jsonPath("$.restaurantDetails.name").isNotEmpty()
            .jsonPath("$.restaurantDetails.phone").isNotEmpty()
            .jsonPath("$.restaurantDetails.addresses").isNotEmpty()
            .jsonPath("$.orderItems").isNotEmpty()
            .jsonPath("$.orderItems").isArray()
            .jsonPath("$.orderItems[0].menuItemId").isNotEmpty()
            .jsonPath("$.orderItems[0].name").isNotEmpty()
            .jsonPath("$.orderItems[0].description").isNotEmpty()
            .jsonPath("$.orderItems[0].price").isNotEmpty()
            .jsonPath("$.orderItems[0].quantity").isNotEmpty();
    }

    /**
    * Test: updateOrder for OK.
    */
    @Test
    public void testUpdateOrder_200() {
        CustomerOrderInputDTO requestBody = new CustomerOrderInputDTO();
        requestBody.setOrderTime(null);
        requestBody.setStatus(null);
        requestBody.setCustomerId(null);
        requestBody.setRestaurantId(null);
        requestBody.setAddressIdentifier(null);
        requestBody.setOrderItems(new java.util.ArrayList<>());
        requestBody.getOrderItems().get(0).setMenuItemId("aaa");
        requestBody.getOrderItems().get(0).setName("aaa");
        requestBody.getOrderItems().get(0).setDescription("aaa");
        requestBody.getOrderItems().get(0).setPrice(BigDecimal.valueOf(0));
        requestBody.getOrderItems().get(0).setQuantity(1);
        var orderId = "";

        webTestClient.method(PUT).uri("/api/orders/{orderId}", orderId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.orderTime").isNotEmpty()
            .jsonPath("$.status").isNotEmpty()
            .jsonPath("$.customerDetails").isNotEmpty()
            .jsonPath("$.customerDetails.customerId").isNotEmpty()
            .jsonPath("$.customerDetails.firstName").isNotEmpty()
            .jsonPath("$.customerDetails.lastName").isNotEmpty()
            .jsonPath("$.customerDetails.email").isNotEmpty()
            .jsonPath("$.customerDetails.phone").isNotEmpty()
            .jsonPath("$.customerDetails.address").isNotEmpty()
            .jsonPath("$.restaurantDetails").isNotEmpty()
            .jsonPath("$.restaurantDetails.restaurantId").isNotEmpty()
            .jsonPath("$.restaurantDetails.name").isNotEmpty()
            .jsonPath("$.restaurantDetails.phone").isNotEmpty()
            .jsonPath("$.restaurantDetails.addresses").isNotEmpty()
            .jsonPath("$.orderItems").isNotEmpty()
            .jsonPath("$.orderItems").isArray()
            .jsonPath("$.orderItems[0].menuItemId").isNotEmpty()
            .jsonPath("$.orderItems[0].name").isNotEmpty()
            .jsonPath("$.orderItems[0].description").isNotEmpty()
            .jsonPath("$.orderItems[0].price").isNotEmpty()
            .jsonPath("$.orderItems[0].quantity").isNotEmpty();
    }

    /**
    * Test: createCustomerOrder for OK.
    */
    @Test
    public void testCreateCustomerOrder_201() {
        CustomerOrderInputDTO requestBody = new CustomerOrderInputDTO();
        requestBody.setOrderTime(null);
        requestBody.setStatus(null);
        requestBody.setCustomerId(null);
        requestBody.setRestaurantId(null);
        requestBody.setAddressIdentifier(null);
        requestBody.setOrderItems(new java.util.ArrayList<>());
        requestBody.getOrderItems().get(0).setMenuItemId("aaa");
        requestBody.getOrderItems().get(0).setName("aaa");
        requestBody.getOrderItems().get(0).setDescription("aaa");
        requestBody.getOrderItems().get(0).setPrice(BigDecimal.valueOf(0));
        requestBody.getOrderItems().get(0).setQuantity(1);

        webTestClient.method(POST).uri("/api/orders")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.orderTime").isNotEmpty()
            .jsonPath("$.status").isNotEmpty()
            .jsonPath("$.customerDetails").isNotEmpty()
            .jsonPath("$.customerDetails.customerId").isNotEmpty()
            .jsonPath("$.customerDetails.firstName").isNotEmpty()
            .jsonPath("$.customerDetails.lastName").isNotEmpty()
            .jsonPath("$.customerDetails.email").isNotEmpty()
            .jsonPath("$.customerDetails.phone").isNotEmpty()
            .jsonPath("$.customerDetails.address").isNotEmpty()
            .jsonPath("$.restaurantDetails").isNotEmpty()
            .jsonPath("$.restaurantDetails.restaurantId").isNotEmpty()
            .jsonPath("$.restaurantDetails.name").isNotEmpty()
            .jsonPath("$.restaurantDetails.phone").isNotEmpty()
            .jsonPath("$.restaurantDetails.addresses").isNotEmpty()
            .jsonPath("$.orderItems").isNotEmpty()
            .jsonPath("$.orderItems").isArray()
            .jsonPath("$.orderItems[0].menuItemId").isNotEmpty()
            .jsonPath("$.orderItems[0].name").isNotEmpty()
            .jsonPath("$.orderItems[0].description").isNotEmpty()
            .jsonPath("$.orderItems[0].price").isNotEmpty()
            .jsonPath("$.orderItems[0].quantity").isNotEmpty();
    }

    /**
    * Test: cancelOrder for OK.
    */
    @Test
    public void testCancelOrder_200() {
        CancelOrderInputDTO requestBody = new CancelOrderInputDTO();
        requestBody.setId(null);
        requestBody.setReason(null);
        var orderId = "";

        webTestClient.method(PUT).uri("/api/orders/{orderId}/cancel", orderId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.orderTime").isNotEmpty()
            .jsonPath("$.status").isNotEmpty()
            .jsonPath("$.customerDetails").isNotEmpty()
            .jsonPath("$.customerDetails.customerId").isNotEmpty()
            .jsonPath("$.customerDetails.firstName").isNotEmpty()
            .jsonPath("$.customerDetails.lastName").isNotEmpty()
            .jsonPath("$.customerDetails.email").isNotEmpty()
            .jsonPath("$.customerDetails.phone").isNotEmpty()
            .jsonPath("$.customerDetails.address").isNotEmpty()
            .jsonPath("$.restaurantDetails").isNotEmpty()
            .jsonPath("$.restaurantDetails.restaurantId").isNotEmpty()
            .jsonPath("$.restaurantDetails.name").isNotEmpty()
            .jsonPath("$.restaurantDetails.phone").isNotEmpty()
            .jsonPath("$.restaurantDetails.addresses").isNotEmpty()
            .jsonPath("$.orderItems").isNotEmpty()
            .jsonPath("$.orderItems").isArray()
            .jsonPath("$.orderItems[0].menuItemId").isNotEmpty()
            .jsonPath("$.orderItems[0].name").isNotEmpty()
            .jsonPath("$.orderItems[0].description").isNotEmpty()
            .jsonPath("$.orderItems[0].price").isNotEmpty()
            .jsonPath("$.orderItems[0].quantity").isNotEmpty();
    }

    /**
    * Test: searchOrders for OK.
    */
    @Test
    public void testSearchOrders_201() {
        OrdersFilterDTO requestBody = new OrdersFilterDTO();
        requestBody.setStatus(null);
        requestBody.setCustomerName(null);
        requestBody.setRestaurantName(null);

        webTestClient.method(POST).uri("/api/orders/search")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody();
    }

}
