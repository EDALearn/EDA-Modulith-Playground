package io.zenwave360.example.customers.mappers;

import io.zenwave360.example.customers.dtos.*;
import io.zenwave360.example.customers.model.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface CustomerDTOsMapper {

    CustomerDTOsMapper INSTANCE = Mappers.getMapper(CustomerDTOsMapper.class);

    // request mappings
    Address asAddress(AddressDTO dto);

    Customer asCustomer(CustomerDTO dto);

    // response mappings

    List<CustomerDTO> asCustomerDTOList(List<Customer> entityList);

    CustomerPaginatedDTO asCustomerPaginatedDTO(Page<Customer> page);

    default Page<CustomerDTO> asCustomerDTOPage(Page<Customer> page) {
        return page.map(this::asCustomerDTO);
    }

    CustomerDTO asCustomerDTO(Customer entity);

}
