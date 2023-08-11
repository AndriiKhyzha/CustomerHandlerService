package com.customer.handling.service.controller;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

//    private static final Integer id = 25;
    private static final String NAME = "Yarema";

    private static Address thisAddress;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testGetCustomer() {
//        given
        Integer id = 25;

        Customer mockedCustomerForGet = Customer.builder()
                .dbId(id)
                .name(NAME)
                .address(thisAddress)
                .build();

        when(customerService.getCustomer(eq(id))).thenReturn(mockedCustomerForGet);

//        when

        Customer actualCustomer = customerController.getCustomer(id);

//        then
        verify(customerService,times(1)).getCustomer(eq(id));
        assertEquals(id, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
        assertEquals(thisAddress, actualCustomer.getAddress());
    }

    @Test
    void deleteCustomer() {
//        given
        Integer id = 33;

        verify(customerService, times(1)).deleteCustomer(id);
    }

    @Test
    void createCustomer() {
//        given
        Integer id = 79;

        Customer mockedCustomerForCreate = Customer.builder()
                .dbId(id)
                .name(NAME)
                .address(thisAddress)
                .build();
        when(customerService.createCustomer(any(Customer.class))).thenReturn(mockedCustomerForCreate);

        Customer requestCustomer = Customer.builder()
                .dbId(id)
                .name(NAME)
                .address(thisAddress)
                .build();

//        when
        Customer actualCustomer = customerController.createCustomer(requestCustomer);
//        then
        verify(customerService,times(1)).createCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertNotNull(capturedCustomer.getDbId());
        assertEquals(NAME, capturedCustomer.getName());
        assertEquals(thisAddress, capturedCustomer.getAddress());

//        assert returned created customer in database
        assertEquals(mockedCustomerForCreate.getDbId(), actualCustomer.getDbId());
        assertEquals(mockedCustomerForCreate.getName(), actualCustomer.getName());
        assertEquals(mockedCustomerForCreate.getAddress(), actualCustomer.getAddress());
    }

    @Test
    void updateCustomer() {
//        given
        Integer id = 73;

        Customer mockedCustomerForUpdate = Customer.builder()
                .dbId(id)
                .name(NAME)
                .address(thisAddress)
                .build();

        when(customerService.updateCustomer(any(Customer.class))).thenReturn(mockedCustomerForUpdate);

        Customer requestCustomer = Customer.builder()
                .dbId(id)
                .name(NAME)
                .address(thisAddress)
                .build();

//        when
        Customer actualCustomer = customerController.updateCustomer(requestCustomer);

//        then
        verify(customerService,times(1)).updateCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertNotNull(capturedCustomer.getDbId());
        assertEquals(NAME,capturedCustomer.getName());
        assertEquals(thisAddress, capturedCustomer.getAddress());

//      assert returned updated customer in database
        assertEquals(mockedCustomerForUpdate.getDbId(), actualCustomer.getDbId());
        assertEquals(mockedCustomerForUpdate.getName(), actualCustomer.getName());
        assertEquals(mockedCustomerForUpdate.getAddress(), actualCustomer.getAddress());
    }
}