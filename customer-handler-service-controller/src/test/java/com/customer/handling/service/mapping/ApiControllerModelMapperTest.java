package com.customer.handling.service.mapping;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.service.api.CustomerData;
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

        assertEquals(customer.getDbId(), actualCustomerData.dbId());
        assertEquals(customer.getName(), actualCustomerData.name());
        assertEquals(customer.getAddress().getDbId(), actualCustomerData.address().dbId());
        assertEquals(customer.getAddress().getCountry(), actualCustomerData.address().country());
        assertEquals(customer.getAddress().getCity(), actualCustomerData.address().city());
        assertEquals(customer.getAddress().getStreet(), actualCustomerData.address().street());
        assertEquals(customer.getAddress().getNumber(), actualCustomerData.address().number());


        CustomerData customerData = TestUtils.readValue(this.getClass(), CustomerData.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_CustomerData.json");
        Customer actualCustomer = apiControllerModelMapper.map(customerData);

        assertEquals(customerData.dbId(), actualCustomer.getDbId());
        assertEquals(customerData.name(), actualCustomer.getName());
        assertEquals(customerData.address().dbId(), actualCustomer.getAddress().getDbId());
        assertEquals(customerData.address().country(), actualCustomer.getAddress().getCountry());
        assertEquals(customerData.address().city(), actualCustomer.getAddress().getCity());
        assertEquals(customerData.address().street(), actualCustomer.getAddress().getStreet());
        assertEquals(customerData.address().number(), actualCustomer.getAddress().getNumber());


        Address address = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_Address.json");
        AddressData actualAddressData = apiControllerModelMapper.map(address);

        assertEquals(address.getDbId(),actualAddressData.dbId());
        assertEquals(address.getCountry(),actualAddressData.country());
        assertEquals(address.getCity(),actualAddressData.city());
        assertEquals(address.getStreet(),actualAddressData.street());
        assertEquals(address.getNumber(),actualAddressData.number());


        AddressData addressData = TestUtils.readValue(this.getClass(), AddressData.class,
                "com/customer/handling/service/controller/mapping/ApiControllerModelMapper/map_AddressData.json");

        Address actualAddress = apiControllerModelMapper.map(addressData);

        assertEquals(addressData.dbId(), actualAddress.getDbId());
        assertEquals(addressData.country(), actualAddress.getCountry());
        assertEquals(addressData.city(), actualAddress.getCity());
        assertEquals(addressData.street(), actualAddress.getStreet());
        assertEquals(addressData.number(), actualAddress.getNumber());
    }
}