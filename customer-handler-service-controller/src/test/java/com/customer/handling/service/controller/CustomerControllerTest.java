package com.customer.handling.service.controller;

import com.customer.handling.service.api.model.CustomerData;
import com.customer.handling.service.exception.RequestNotValidException;
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
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private static final Integer ID = 25;
    private Integer Id = 0;

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
        ResponseEntity<CustomerData> actualCustomer = customerController.getCustomer(ID);

        //then
        verify(customerService,times(1)).getCustomer(eq(ID));
        assertEquals(mockedCustomerForGet.getDbId(), actualCustomer.getBody().getDbId());
        assertEquals(mockedCustomerForGet.getName(), actualCustomer.getBody().getName());
        assertEquals(mockedCustomerForGet.getAddress().getDbId(), actualCustomer.getBody().getAddress().getDbId());
        assertEquals(mockedCustomerForGet.getAddress().getCountry(), actualCustomer.getBody().getAddress().getCountry());
        assertEquals(mockedCustomerForGet.getAddress().getCity(), actualCustomer.getBody().getAddress().getCity());
        assertEquals(mockedCustomerForGet.getAddress().getStreet(), actualCustomer.getBody().getAddress().getStreet());
        assertEquals(mockedCustomerForGet.getAddress().getNumber(), actualCustomer.getBody().getAddress().getNumber());
    }

    @Test
    void getCustomerWithZeroDbId() {
       assertThrows(RequestNotValidException.class,
                () -> customerController.getCustomer(0),
                "DbId must not be Zero"
        );
        //then
        verify(customerService, never()).getCustomer(eq(Id));
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
        ResponseEntity<CustomerData> actualCustomer = customerController.createCustomer(requestCustomer);

        // then
        verify(customerService,times(1)).createCustomer(customerArgumentCaptor.capture());
        //
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertNull(capturedCustomer.getDbId());
        assertEquals(requestCustomer.getName(), capturedCustomer.getName());
        assertEquals(requestCustomer.getAddress().getDbId(), capturedCustomer.getAddress().getDbId());
        assertEquals(requestCustomer.getAddress().getCountry(), capturedCustomer.getAddress().getCountry());
        assertEquals(requestCustomer.getAddress().getCity(), capturedCustomer.getAddress().getCity());
        assertEquals(requestCustomer.getAddress().getStreet(), capturedCustomer.getAddress().getStreet());
        assertEquals(requestCustomer.getAddress().getNumber(), capturedCustomer.getAddress().getNumber());

        //assert returned created customer in database
        assertEquals(createdCustomer.getDbId(), actualCustomer.getBody().getDbId());
        assertEquals(createdCustomer.getName(), actualCustomer.getBody().getName());
        assertEquals(createdCustomer.getAddress().getDbId(), actualCustomer.getBody().getAddress().getDbId());
        assertEquals(createdCustomer.getAddress().getCountry(), actualCustomer.getBody().getAddress().getCountry());
        assertEquals(createdCustomer.getAddress().getCity(), actualCustomer.getBody().getAddress().getCity());
        assertEquals(createdCustomer.getAddress().getStreet(), actualCustomer.getBody().getAddress().getStreet());
        assertEquals(createdCustomer.getAddress().getNumber(), actualCustomer.getBody().getAddress().getNumber());
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
        ResponseEntity <CustomerData> actualCustomer = customerController.updateCustomer(requestCustomer);

        //then
        verify(customerService).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        //
        assertEquals(requestCustomer.getDbId(), capturedCustomer.getDbId());
        assertEquals(requestCustomer.getName(), capturedCustomer.getName());
        assertEquals(requestCustomer.getAddress().getDbId(), capturedCustomer.getAddress().getDbId());
        assertEquals(requestCustomer.getAddress().getCountry(), capturedCustomer.getAddress().getCountry());
        assertEquals(requestCustomer.getAddress().getCity(), capturedCustomer.getAddress().getCity());
        assertEquals(requestCustomer.getAddress().getStreet(), capturedCustomer.getAddress().getStreet());
        assertEquals(requestCustomer.getAddress().getNumber(), capturedCustomer.getAddress().getNumber());

        //assert returned updated customer in database
        assertEquals(updatedCustomer.getDbId(), actualCustomer.getBody().getDbId());
        assertEquals(updatedCustomer.getName(), actualCustomer.getBody().getName());
        assertEquals(updatedCustomer.getAddress().getDbId(), actualCustomer.getBody().getAddress().getDbId());
        assertEquals(updatedCustomer.getAddress().getCountry(), actualCustomer.getBody().getAddress().getCountry());
        assertEquals(updatedCustomer.getAddress().getCity(), actualCustomer.getBody().getAddress().getCity());
        assertEquals(updatedCustomer.getAddress().getStreet(), actualCustomer.getBody().getAddress().getStreet());
        assertEquals(updatedCustomer.getAddress().getNumber(), actualCustomer.getBody().getAddress().getNumber());
    }
}