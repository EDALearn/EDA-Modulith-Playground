package io.zenwave360.example.customers;

import io.zenwave360.example.customers.*;
import io.zenwave360.example.customers.dtos.*;
import io.zenwave360.example.customers.base.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.*;

/**
* Integration tests for the {@link CustomerApi} REST controller.
*/
public class CustomerApiIT extends BaseWebTestClientTest {



    /**
    * Test: Create customer javadoc comment for OK.
    */
    @Test
    public void testCreateCustomer_201() {
        CustomerDTO requestBody = new CustomerDTO();
        requestBody.setId(null);
        requestBody.setVersion(null);
        requestBody.setFirstName(null);
        requestBody.setLastName(null);
        requestBody.setEmail(null);
        requestBody.setPhone(null);
        requestBody.setAddresses(new java.util.ArrayList<>());
        requestBody.getAddresses().get(0).setIdentifier("aaa");
        requestBody.getAddresses().get(0).setStreet("aaa");
        requestBody.getAddresses().get(0).setCity("aaa");
        requestBody.getAddresses().get(0).setState("aaa");
        requestBody.getAddresses().get(0).setZip("aaa");
        requestBody.getAddresses().get(0).setType(AddressTypeDTO.HOME);

        webTestClient.method(POST).uri("/api/customers")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.firstName").isNotEmpty()
            .jsonPath("$.lastName").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
            .jsonPath("$.addresses").isArray()
            .jsonPath("$.addresses[0].identifier").isNotEmpty()
            .jsonPath("$.addresses[0].street").isNotEmpty()
            .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.addresses[0].state").isNotEmpty()
            .jsonPath("$.addresses[0].zip").isNotEmpty()
            .jsonPath("$.addresses[0].type").isNotEmpty();
    }

    /**
    * Test: listCustomers for OK.
    */
    @Test
    public void testListCustomers_200() {
        var page = "";
        var limit = "";
        var sort = "";

        webTestClient.method(GET).uri(uriBuilder -> uriBuilder.path("/api/customers").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort))
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
            .jsonPath("$.content[0].firstName").isNotEmpty()
            .jsonPath("$.content[0].lastName").isNotEmpty()
            .jsonPath("$.content[0].email").isNotEmpty()
            .jsonPath("$.content[0].phone").isNotEmpty()
            .jsonPath("$.content[0].addresses").isNotEmpty();
    }

    /**
    * Test: updateCustomer for OK.
    */
    @Test
    public void testUpdateCustomer_200() {
        CustomerDTO requestBody = new CustomerDTO();
        requestBody.setId(null);
        requestBody.setVersion(null);
        requestBody.setFirstName(null);
        requestBody.setLastName(null);
        requestBody.setEmail(null);
        requestBody.setPhone(null);
        requestBody.setAddresses(new java.util.ArrayList<>());
        requestBody.getAddresses().get(0).setIdentifier("aaa");
        requestBody.getAddresses().get(0).setStreet("aaa");
        requestBody.getAddresses().get(0).setCity("aaa");
        requestBody.getAddresses().get(0).setState("aaa");
        requestBody.getAddresses().get(0).setZip("aaa");
        requestBody.getAddresses().get(0).setType(AddressTypeDTO.WORK);
        var customerId = "";

        webTestClient.method(PUT).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.firstName").isNotEmpty()
            .jsonPath("$.lastName").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
            .jsonPath("$.addresses").isArray()
            .jsonPath("$.addresses[0].identifier").isNotEmpty()
            .jsonPath("$.addresses[0].street").isNotEmpty()
            .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.addresses[0].state").isNotEmpty()
            .jsonPath("$.addresses[0].zip").isNotEmpty()
            .jsonPath("$.addresses[0].type").isNotEmpty();
    }

    /**
    * Test: deleteCustomer for OK.
    */
    @Test
    public void testDeleteCustomer_204() {
        var customerId = "";

        webTestClient.method(DELETE).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204);
    }

    /**
    * Test: getCustomer for OK.
    */
    @Test
    public void testGetCustomer_200() {
        var customerId = "";

        webTestClient.method(GET).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.firstName").isNotEmpty()
            .jsonPath("$.lastName").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
            .jsonPath("$.addresses").isArray()
            .jsonPath("$.addresses[0].identifier").isNotEmpty()
            .jsonPath("$.addresses[0].street").isNotEmpty()
            .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.addresses[0].state").isNotEmpty()
            .jsonPath("$.addresses[0].zip").isNotEmpty()
            .jsonPath("$.addresses[0].type").isNotEmpty();
    }

    /**
    * Test: Updates a the customer address identified by address.identifier for OK.
    */
    @Test
    public void testUpdateCustomerAddress_200() {
        AddressDTO requestBody = new AddressDTO();
        requestBody.setIdentifier(null);
        requestBody.setStreet(null);
        requestBody.setCity(null);
        requestBody.setState(null);
        requestBody.setZip(null);
        requestBody.setType(null);
        var customerId = "";
        var identifier = "";

        webTestClient.method(PUT).uri("/api/customers/{customerId}/address/{identifier}", customerId, identifier)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.firstName").isNotEmpty()
            .jsonPath("$.lastName").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.phone").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
            .jsonPath("$.addresses").isArray()
            .jsonPath("$.addresses[0].identifier").isNotEmpty()
            .jsonPath("$.addresses[0].street").isNotEmpty()
            .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.addresses[0].state").isNotEmpty()
            .jsonPath("$.addresses[0].zip").isNotEmpty()
            .jsonPath("$.addresses[0].type").isNotEmpty();
    }

}
