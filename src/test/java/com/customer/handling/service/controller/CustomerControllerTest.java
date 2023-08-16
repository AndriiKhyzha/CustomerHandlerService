package com.customer.handling.service.controller;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
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
    private static final String NAME = "Yarema";
    private static Address ADDRESS;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        ADDRESS = new Address();
    }

    @Test
    void getCustomer() {
        //given
        Customer mockedCustomerForGet = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .address(ADDRESS)
                .build();

        when(customerService.getCustomer(eq(ID))).thenReturn(mockedCustomerForGet);

        //when
        Customer actualCustomer = customerController.getCustomer(ID);

        //then
        verify(customerService,times(1)).getCustomer(eq(ID));
        assertEquals(ID, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
        assertEquals(ADDRESS, actualCustomer.getAddress());
    }

    @Test
    void deleteCustomer() {
        //when
        customerController.deleteCustomer(ID);
        //then
        verify(customerService, times(1)).deleteCustomer(ID);
    }

    @Test
    void createCustomer() {
        //given
        Customer createdCustomer = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .address(ADDRESS)
                .build();
        when(customerService.createCustomer(any(Customer.class))).thenReturn(createdCustomer);

        Customer requestCustomer = Customer.builder()
                .name(NAME)
                .address(ADDRESS)
                .build();

        //when
        Customer actualCustomer = customerController.createCustomer(requestCustomer);

        // then
        verify(customerService,times(1)).createCustomer(customerArgumentCaptor.capture());
        //
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertNull(capturedCustomer.getDbId());
        assertEquals(NAME, capturedCustomer.getName());
        assertEquals(ADDRESS, capturedCustomer.getAddress());

        //assert returned created customer in database
        assertEquals(createdCustomer.getDbId(), actualCustomer.getDbId());
        assertEquals(createdCustomer.getName(), actualCustomer.getName());
        assertEquals(createdCustomer.getAddress(), actualCustomer.getAddress());
    }

    @Test
    void updateCustomer() {
        //given
        Customer updatedCustomer = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .address(ADDRESS)
                .build();
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        Customer requestCustomer = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .address(ADDRESS)
                .build();

        //when
        Customer actualCustomer = customerController.updateCustomer(requestCustomer);

        //then
        verify(customerService,times(1)).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        //
        assertEquals(ID, capturedCustomer.getDbId());
        assertEquals(NAME,capturedCustomer.getName());
        assertEquals(ADDRESS, capturedCustomer.getAddress());

        //assert returned updated customer in database
        assertEquals(ID, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
        assertEquals(ADDRESS, actualCustomer.getAddress());
    }
}