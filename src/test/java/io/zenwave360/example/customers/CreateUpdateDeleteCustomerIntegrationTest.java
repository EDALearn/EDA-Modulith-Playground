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
* Business Flow Test for: createCustomer, updateCustomer, deleteCustomer, getCustomer.
*/
public class CreateUpdateDeleteCustomerIntegrationTest extends BaseWebTestClientTest {

    /**
    * Business Flow Test for: createCustomer, updateCustomer, deleteCustomer, getCustomer.
    */
    @Test
    public void testCreateUpdateDeleteCustomerIntegrationTest() {
        // createCustomer: Create customer javadoc comment
        CustomerDTO customerRequestBody0 = new CustomerDTO();
        customerRequestBody0.setId(null);
        customerRequestBody0.setVersion(null);
        customerRequestBody0.setFirstName(null);
        customerRequestBody0.setLastName(null);
        customerRequestBody0.setEmail(null);
        customerRequestBody0.setPhone(null);
        customerRequestBody0.setAddresses(new java.util.ArrayList<>());
        customerRequestBody0.getAddresses().get(0).setIdentifier("aaa");
        customerRequestBody0.getAddresses().get(0).setStreet("aaa");
        customerRequestBody0.getAddresses().get(0).setCity("aaa");
        customerRequestBody0.getAddresses().get(0).setState("aaa");
        customerRequestBody0.getAddresses().get(0).setZip("aaa");
        customerRequestBody0.getAddresses().get(0).setType(new AddressTypeDTO());

        var createCustomerResponse0 = webTestClient.method(POST).uri("/api/customers")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(customerRequestBody0)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO.class);

    
        // updateCustomer: updateCustomer
        CustomerDTO customerRequestBody1 = new CustomerDTO();
        customerRequestBody1.setId(null);
        customerRequestBody1.setVersion(null);
        customerRequestBody1.setFirstName(null);
        customerRequestBody1.setLastName(null);
        customerRequestBody1.setEmail(null);
        customerRequestBody1.setPhone(null);
        customerRequestBody1.setAddresses(new java.util.ArrayList<>());
        customerRequestBody1.getAddresses().get(0).setIdentifier("aaa");
        customerRequestBody1.getAddresses().get(0).setStreet("aaa");
        customerRequestBody1.getAddresses().get(0).setCity("aaa");
        customerRequestBody1.getAddresses().get(0).setState("aaa");
        customerRequestBody1.getAddresses().get(0).setZip("aaa");
        customerRequestBody1.getAddresses().get(0).setType(new AddressTypeDTO());
        var customerId1 = "";

        var updateCustomerResponse1 = webTestClient.method(PUT).uri("/api/customers/{customerId}", customerId1)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(customerRequestBody1)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO.class);

    
        // deleteCustomer: deleteCustomer
        var customerId2 = "";

        webTestClient.method(DELETE).uri("/api/customers/{customerId}", customerId2)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204);

    
        // getCustomer: getCustomer
        var customerId3 = "";

        var getCustomerResponse3 = webTestClient.method(GET).uri("/api/customers/{customerId}", customerId3)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO.class);

    
    }


}
