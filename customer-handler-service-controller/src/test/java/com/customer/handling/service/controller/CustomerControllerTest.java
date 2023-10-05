package com.customer.handling.service.controller;

import com.customer.handling.service.api.CustomerData;
import com.customer.handling.service.models.Customer;
import com.customer.handling.service.service.CustomerService;
import com.customer.handling.service.test.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private static final Integer ID = 25;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void getCustomer() {
        //given
        Customer mockedCustomerForGet = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/getCustomer_mockedCustomerForGet.json");
        when(customerService.getCustomer(eq(ID))).thenReturn(mockedCustomerForGet);

        //when
        CustomerData actualCustomer = customerController.getCustomer(ID);

        //then
        verify(customerService,times(1)).getCustomer(eq(ID));
        assertEquals(mockedCustomerForGet.getDbId(), actualCustomer.dbId());
        assertEquals(mockedCustomerForGet.getName(), actualCustomer.name());
        assertEquals(mockedCustomerForGet.getAddress().getDbId(), actualCustomer.address().dbId());
        assertEquals(mockedCustomerForGet.getAddress().getCountry(), actualCustomer.address().country());
        assertEquals(mockedCustomerForGet.getAddress().getCity(), actualCustomer.address().city());
        assertEquals(mockedCustomerForGet.getAddress().getStreet(), actualCustomer.address().street());
        assertEquals(mockedCustomerForGet.getAddress().getNumber(), actualCustomer.address().number());
    }

    @Test
    void deleteCustomer() {
        //when
        customerController.deleteCustomer(ID);
        //then
        verify(customerService).deleteCustomer(ID);
    }

    @Test
    void createCustomer() {
        //given
        Customer createdCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/createCustomer_createdCustomer.json");
        when(customerService.createCustomer(any(Customer.class))).thenReturn(createdCustomer);

        CustomerData requestCustomer = TestUtils.readValue(this.getClass(), CustomerData.class,
                "com/customer/handling/service/controller/customer/createCustomer_requestCustomer.json");

        //when
        CustomerData actualCustomer = customerController.createCustomer(requestCustomer);

        // then
        verify(customerService,times(1)).createCustomer(customerArgumentCaptor.capture());
        //
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertNull(capturedCustomer.getDbId());
        assertEquals(requestCustomer.name(), capturedCustomer.getName());
        assertEquals(requestCustomer.address().dbId(), capturedCustomer.getAddress().getDbId());
        assertEquals(requestCustomer.address().country(), capturedCustomer.getAddress().getCountry());
        assertEquals(requestCustomer.address().city(), capturedCustomer.getAddress().getCity());
        assertEquals(requestCustomer.address().street(), capturedCustomer.getAddress().getStreet());
        assertEquals(requestCustomer.address().number(), capturedCustomer.getAddress().getNumber());

        //assert returned created customer in database
        assertEquals(createdCustomer.getDbId(), actualCustomer.dbId());
        assertEquals(createdCustomer.getName(), actualCustomer.name());
        assertEquals(createdCustomer.getAddress().getDbId(), actualCustomer.address().dbId());
        assertEquals(createdCustomer.getAddress().getCountry(), actualCustomer.address().country());
        assertEquals(createdCustomer.getAddress().getCity(), actualCustomer.address().city());
        assertEquals(createdCustomer.getAddress().getStreet(), actualCustomer.address().street());
        assertEquals(createdCustomer.getAddress().getNumber(), actualCustomer.address().number());
    }

    @Test
    void updateCustomer() {
        //given
        Customer updatedCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/updateCustomer_updatedCustomer.json");
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        CustomerData requestCustomer = TestUtils.readValue(this.getClass(), CustomerData.class,
                "com/customer/handling/service/controller/customer/updateCustomer_requestCustomer.json");

        //when
        CustomerData actualCustomer = customerController.updateCustomer(requestCustomer);

        //then
        verify(customerService).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        //
        assertEquals(requestCustomer.dbId(), capturedCustomer.getDbId());
        assertEquals(requestCustomer.name(), capturedCustomer.getName());
        assertEquals(requestCustomer.address().dbId(), capturedCustomer.getAddress().getDbId());
        assertEquals(requestCustomer.address().country(), capturedCustomer.getAddress().getCountry());
        assertEquals(requestCustomer.address().city(), capturedCustomer.getAddress().getCity());
        assertEquals(requestCustomer.address().street(), capturedCustomer.getAddress().getStreet());
        assertEquals(requestCustomer.address().number(), capturedCustomer.getAddress().getNumber());

        //assert returned updated customer in database
        assertEquals(updatedCustomer.getDbId(), actualCustomer.dbId());
        assertEquals(updatedCustomer.getName(), actualCustomer.name());
        assertEquals(updatedCustomer.getAddress().getDbId(), actualCustomer.address().dbId());
        assertEquals(updatedCustomer.getAddress().getCountry(), actualCustomer.address().country());
        assertEquals(updatedCustomer.getAddress().getCity(), actualCustomer.address().city());
        assertEquals(updatedCustomer.getAddress().getStreet(), actualCustomer.address().street());
        assertEquals(updatedCustomer.getAddress().getNumber(), actualCustomer.address().number());
    }
}