package com.customer.handling.service.mapping;

import com.customer.handling.service.api.model.AddressData;
import com.customer.handling.service.api.model.CustomerData;
import com.customer.handling.service.models.Address;
import com.customer.handling.service.models.Customer;
import com.customer.handling.service.test.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiControllerModelMapperTest {

    private final ApiControllerModelMapper apiControllerModelMapper = Mappers.getMapper(ApiControllerModelMapper.class);

    @Test
    void map() {
        Customer customer  = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_Customer.json");
        CustomerData actualCustomerData = apiControllerModelMapper.map(customer);

        assertEquals(customer.getDbId(), actualCustomerData.getDbId());
        assertEquals(customer.getName(), actualCustomerData.getName());
        assertEquals(customer.getAddress().getDbId(), actualCustomerData.getAddress().getDbId());
        assertEquals(customer.getAddress().getCountry(), actualCustomerData.getAddress().getCountry());
        assertEquals(customer.getAddress().getCity(), actualCustomerData.getAddress().getCity());
        assertEquals(customer.getAddress().getStreet(), actualCustomerData.getAddress().getStreet());
        assertEquals(customer.getAddress().getNumber(), actualCustomerData.getAddress().getNumber());


        CustomerData customerData = TestUtils.readValue(this.getClass(), CustomerData.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_CustomerData.json");
        Customer actualCustomer = apiControllerModelMapper.map(customerData);

        assertEquals(customerData.getDbId(), actualCustomer.getDbId());
        assertEquals(customerData.getName(), actualCustomer.getName());
        assertEquals(customerData.getAddress().getDbId(), actualCustomer.getAddress().getDbId());
        assertEquals(customerData.getAddress().getCountry(), actualCustomer.getAddress().getCountry());
        assertEquals(customerData.getAddress().getCity(), actualCustomer.getAddress().getCity());
        assertEquals(customerData.getAddress().getStreet(), actualCustomer.getAddress().getStreet());
        assertEquals(customerData.getAddress().getNumber(), actualCustomer.getAddress().getNumber());


        Address address = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_Address.json");
        AddressData actualAddressData = apiControllerModelMapper.map(address);

        assertEquals(address.getDbId(),actualAddressData.getDbId());
        assertEquals(address.getCountry(),actualAddressData.getCountry());
        assertEquals(address.getCity(),actualAddressData.getCity());
        assertEquals(address.getStreet(),actualAddressData.getStreet());
        assertEquals(address.getNumber(),actualAddressData.getNumber());


        AddressData addressData = TestUtils.readValue(this.getClass(), AddressData.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_AddressData.json");

        Address actualAddress = apiControllerModelMapper.map(addressData);

        assertEquals(addressData.getDbId(), actualAddress.getDbId());
        assertEquals(addressData.getCountry(), actualAddress.getCountry());
        assertEquals(addressData.getCity(), actualAddress.getCity());
        assertEquals(addressData.getStreet(), actualAddress.getStreet());
        assertEquals(addressData.getNumber(), actualAddress.getNumber());
    }
}