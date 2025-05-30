package io.zenwave360.example.customers;

import io.zenwave360.example.customers.dtos.*;
import io.zenwave360.example.customers.events.*;
import io.zenwave360.example.customers.mappers.*;
import io.zenwave360.example.customers.model.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Implementation for managing [Customer]. */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CustomerServiceMapper customerServiceMapper = CustomerServiceMapper.INSTANCE;

    private final CustomerRepository customerRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;

    private final EventPublisher eventPublisher;

    @Transactional
    public Customer createCustomer(Customer input) {
        log.debug("[CRUD] Request to save Customer: {}", input);
        var customer = customerServiceMapper.update(new Customer(), input);
        customer = customerRepository.save(customer);
        // emit events
        var customerEvent = eventsMapper.asCustomerEvent(customer);
        eventPublisher.onCustomerEvent(customerEvent);
        return customer;
    }

    @Transactional
    public Optional<Customer> updateCustomer(String id, Customer input) {
        log.debug("Request updateCustomer: {} {}", id, input);

        var customer = customerRepository.findById(id).map(existingCustomer -> {
            return customerServiceMapper.update(existingCustomer, input);
        }).map(customerRepository::save);
        if (customer.isPresent()) {
            // emit events
            var customerEvent = eventsMapper.asCustomerEvent(customer.get());
            eventPublisher.onCustomerEvent(customerEvent);
        }
        return customer;
    }

    @Transactional
    public Optional<Customer> updateCustomerAddress(String id, String identifier, Address address) {
        log.debug("Request updateCustomerAddress: {} {} {}", id, identifier, address);

        var customer = customerRepository.findById(id).map(existingCustomer -> {
            return customerServiceMapper.update(existingCustomer, identifier, address);
        }).map(customerRepository::save);
        if (customer.isPresent()) {
            // emit events
            var customerEvent = eventsMapper.asCustomerEvent(customer.get());
            eventPublisher.onCustomerEvent(customerEvent);
            var customerAddressUpdated = eventsMapper.asCustomerAddressUpdated(customer.get());
            eventPublisher.onCustomerAddressUpdated(customerAddressUpdated);
        }
        return customer;
    }

    @Transactional
    public void deleteCustomer(String id) {
        log.debug("[CRUD] Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
        // emit events
        var customerEvent = eventsMapper.asCustomerEvent(id);
        eventPublisher.onCustomerEvent(customerEvent);
    }

    @Transactional
    public Optional<Customer> getCustomer(String id) {
        log.debug("[CRUD] Request to get Customer : {}", id);
        var customer = customerRepository.findById(id);
        return customer;
    }

    public Page<Customer> listCustomers(Pageable pageable) {
        log.debug("Request listCustomers: {}", pageable);

        var customers = customerRepository.findAll(pageable);
        return customers;
    }

}
