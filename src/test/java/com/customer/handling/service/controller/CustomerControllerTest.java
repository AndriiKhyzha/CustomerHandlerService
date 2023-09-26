package com.customer.handling.service.controller;

import com.customer.handling.service.TestUtils;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.services.CustomerService;
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
        Customer actualCustomer = customerController.getCustomer(ID);

        //then
        verify(customerService,times(1)).getCustomer(eq(ID));
        assertEquals(mockedCustomerForGet.getDbId(), actualCustomer.getDbId());
        assertEquals(mockedCustomerForGet.getName(), actualCustomer.getName());
        assertEquals(mockedCustomerForGet.getAddress().getDbId(), actualCustomer.getAddress().getDbId());
        assertEquals(mockedCustomerForGet.getAddress().getCountry(), actualCustomer.getAddress().getCountry());
        assertEquals(mockedCustomerForGet.getAddress().getCity(), actualCustomer.getAddress().getCity());
        assertEquals(mockedCustomerForGet.getAddress().getStreet(), actualCustomer.getAddress().getStreet());
        assertEquals(mockedCustomerForGet.getAddress().getNumber(), actualCustomer.getAddress().getNumber());
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

        Customer requestCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/createCustomer_requestCustomer.json");

        //when
        Customer actualCustomer = customerController.createCustomer(requestCustomer);

        // then
        verify(customerService,times(1)).createCustomer(customerArgumentCaptor.capture());
        //
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertNull(capturedCustomer.getDbId());
        assertEquals(requestCustomer.getName(), capturedCustomer.getName());
        assertEquals(requestCustomer.getAddress(), capturedCustomer.getAddress());

        //assert returned created customer in database
        assertEquals(createdCustomer.getDbId(), actualCustomer.getDbId());
        assertEquals(createdCustomer.getName(), actualCustomer.getName());
        assertEquals(createdCustomer.getAddress(), actualCustomer.getAddress());
    }

    @Test
    void updateCustomer() {
        //given
        Customer updatedCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/updateCustomer_updatedCustomer.json");
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        Customer requestCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/controller/customer/updateCustomer_requestCustomer.json");

        //when
        Customer actualCustomer = customerController.updateCustomer(requestCustomer);

        //then
        verify(customerService).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        //
        assertEquals(requestCustomer.getDbId(), capturedCustomer.getDbId());
        assertEquals(requestCustomer.getName(), capturedCustomer.getName());
        assertEquals(requestCustomer.getAddress(), capturedCustomer.getAddress());

        //assert returned updated customer in database
        assertEquals(updatedCustomer.getDbId(), actualCustomer.getDbId());
        assertEquals(updatedCustomer.getName(), actualCustomer.getName());
        assertEquals(updatedCustomer.getAddress(), actualCustomer.getAddress());
    }
}