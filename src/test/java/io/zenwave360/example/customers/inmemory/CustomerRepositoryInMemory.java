package io.zenwave360.example.customers.inmemory;

import io.zenwave360.example.customers.CustomerRepository;
import io.zenwave360.example.customers.model.*;

public class CustomerRepositoryInMemory extends InMemoryMongodbRepository<Customer> implements CustomerRepository {

}
