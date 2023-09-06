package com.customer.handling.service.services;

import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.CustomerDB;
import com.customer.handling.service.database.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private final Integer ID = 13;
    private final String NAME = "Yarema";

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Captor
    private ArgumentCaptor<CustomerDB> customerDBArgumentCaptor;

    @Test
    void getCustomer() {
        // given
        CustomerDB mockedGetCustomerDB = CustomerDB.builder()
                .id(ID)
                .name(NAME)
                .build();

        when(customerRepository.findById(eq(ID))).thenReturn(Optional.of(mockedGetCustomerDB));

        // when
        Customer actualCustomer = customerServiceImpl.getCustomer(ID);

        // then
        verify(customerRepository).findById(eq(ID));

        assertEquals(ID, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
    }

    @Test
    void createCustomer() {
        // given
        CustomerDB createdCustomerDB = CustomerDB.builder()
                .id(ID)
                .name(NAME)
                .build();
        when(customerRepository.save(any(CustomerDB.class))).thenReturn(createdCustomerDB);

        Customer customerToCreate = Customer.builder()
                .name(NAME)
                .build();

        // when
        Customer actualCustomer = customerServiceImpl.createCustomer(customerToCreate);

        // then
        verify(customerRepository).save(customerDBArgumentCaptor.capture());
        CustomerDB capturedCustomerDB = customerDBArgumentCaptor.getValue();

        assertNull(capturedCustomerDB.getId());
        assertEquals(NAME, capturedCustomerDB.getName());

        assertEquals(ID, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
    }

    @Test
    void updateCustomer() {
        // given
        CustomerDB findedByIdCustomerDB = CustomerDB.builder()
                .id(ID)
                .name(NAME)
                .build();
        when(customerRepository.findById(eq(ID))).thenReturn(Optional.of(findedByIdCustomerDB));

        CustomerDB updatedCustomerDB = CustomerDB.builder()
                .id(ID)
                .name(NAME)
                .build();
        when(customerRepository.save(any(CustomerDB.class))).thenReturn(updatedCustomerDB);

        Customer customerToUpdate = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .build();

        // when
        Customer actualCustomer = customerServiceImpl.updateCustomer(customerToUpdate);

        //then
        verify(customerRepository).findById(ID);

        verify(customerRepository).save(customerDBArgumentCaptor.capture());
        CustomerDB capturedCustomerDB = customerDBArgumentCaptor.getValue();
        assertEquals(ID, capturedCustomerDB.getId());
        assertEquals(NAME, capturedCustomerDB.getName());

        assertEquals(ID, actualCustomer.getDbId());
        assertEquals(NAME, actualCustomer.getName());
    }

    @Test
    void deleteCustomer() {
        //when
        customerServiceImpl.deleteCustomer(ID);
        //then
        verify(customerRepository).deleteById(eq(ID));
    }
}