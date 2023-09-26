package com.customer.handling.service.services;

import com.customer.handling.service.TestUtils;
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

    private final Integer ID = 91;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Captor
    private ArgumentCaptor<CustomerDB> customerDBArgumentCaptor;

    @Test
    void getCustomer() {
        // given
        CustomerDB mockedGetCustomerDB = TestUtils.readValue(this.getClass(), CustomerDB.class,
                "com/customer/handling/service/services/customer/getCustomer_mockedGetCustomerDB.json");

        when(customerRepository.findById(eq(ID))).thenReturn(Optional.of(mockedGetCustomerDB));

        // when
        Customer actualCustomer = customerServiceImpl.getCustomer(ID);

        // then
        verify(customerRepository).findById(eq(ID));

        assertEquals(mockedGetCustomerDB.getId(), actualCustomer.getDbId());
        assertEquals(mockedGetCustomerDB.getName(), actualCustomer.getName());
    }

    @Test
    void createCustomer() {
        // given
        CustomerDB createdCustomerDB = TestUtils.readValue(this.getClass(), CustomerDB.class,
                "com/customer/handling/service/services/customer/createCustomer_createdCustomer.json");
        when(customerRepository.save(any(CustomerDB.class))).thenReturn(createdCustomerDB);

        Customer customerToCreate = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/services/customer/createCustomer_customerToCreate.json");

        // when
        Customer actualCustomer = customerServiceImpl.createCustomer(customerToCreate);

        // then
        verify(customerRepository).save(customerDBArgumentCaptor.capture());
        CustomerDB capturedCustomerDB = customerDBArgumentCaptor.getValue();

        assertNull(capturedCustomerDB.getId());
        assertEquals(customerToCreate.getName(), capturedCustomerDB.getName());

        assertEquals(createdCustomerDB.getId(), actualCustomer.getDbId());
        assertEquals(createdCustomerDB.getName(), actualCustomer.getName());
    }

    @Test
    void updateCustomer() {
        // given
        CustomerDB foundedByIdCustomerDB = TestUtils.readValue(this.getClass(), CustomerDB.class,
                "com/customer/handling/service/services/customer/updateCustomer_foundedByIdCustomer.json");
        when(customerRepository.findById(eq(ID))).thenReturn(Optional.of(foundedByIdCustomerDB));

        CustomerDB updatedCustomerDB = TestUtils.readValue(this.getClass(), CustomerDB.class,
                "com/customer/handling/service/services/customer/updateCustomer_updatedCustomerDB.json");
        when(customerRepository.save(any(CustomerDB.class))).thenReturn(updatedCustomerDB);

        Customer customerToUpdate = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/services/customer/updateCustomer_customerToUpdate.json");

        // when
        Customer actualCustomer = customerServiceImpl.updateCustomer(customerToUpdate);

        //then
        verify(customerRepository).findById(ID);

        verify(customerRepository).save(customerDBArgumentCaptor.capture());
        CustomerDB capturedCustomerDB = customerDBArgumentCaptor.getValue();
        assertEquals(customerToUpdate.getDbId(), capturedCustomerDB.getId());
        assertEquals(customerToUpdate.getName(), capturedCustomerDB.getName());

        assertEquals(updatedCustomerDB.getId(), actualCustomer.getDbId());
        assertEquals(updatedCustomerDB.getName(), actualCustomer.getName());
    }

    @Test
    void deleteCustomer() {
        //when
        customerServiceImpl.deleteCustomer(ID);
        //then
        verify(customerRepository).deleteById(eq(ID));
    }
}