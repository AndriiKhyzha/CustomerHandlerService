package com.customer.handling.service.controller;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.services.AddressService;
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
class AddressControllerTest {

    private static final String COUNTRY = "Canada";
    private static final String CITY = "Calgary";
    private static final String STREET = "Calgary Ave.";
    private static final String NUMBER = "149";

//     Todo: read about mockito.*, Captor
    @Mock
    private AddressService addressService;

    @Captor
    private ArgumentCaptor<Address> addressArgumentCaptor;

    @InjectMocks
    private AddressController addressController;

    @Test
    void testCreateAddress() {
        // given
        Integer id = 73;
        Address createdAddress = Address.builder()
                .dbId(id)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();
        when(addressService.createAddress(any(Address.class))).thenReturn(createdAddress);

        Address requestAddress = Address.builder()
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        // when
        Address actualAddress = addressController.createAddress(requestAddress);

        // then
        verify(addressService, times(1)).createAddress(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();

        assertNull(capturedAddress.getDbId());
        assertEquals(COUNTRY, capturedAddress.getCountry());
        assertEquals(CITY, capturedAddress.getCity());
        assertEquals(STREET, capturedAddress.getStreet());
        assertEquals(NUMBER, capturedAddress.getNumber());

        // assert returned created address in database
        assertEquals(createdAddress.getDbId(), actualAddress.getDbId());
        assertEquals(createdAddress.getCountry(), actualAddress.getCountry());
        assertEquals(createdAddress.getCity(), actualAddress.getCity());
        assertEquals(createdAddress.getStreet(), actualAddress.getStreet());
        assertEquals(createdAddress.getNumber(), actualAddress.getNumber());
    }

    @Test
    void testGetAddress() {
        //given
        Integer id = 735;
        Address address = Address.builder()
                .dbId(id)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        when(addressService.getAddress(eq(id))).thenReturn(address);

        //when
        Address actualAddress = addressController.getAddress(id);

        //then
        verify(addressService, times(1)).getAddress(eq(id));
        assertEquals(COUNTRY, actualAddress.getCountry());
        assertEquals(CITY, actualAddress.getCity());
        assertEquals(STREET, actualAddress.getStreet());
        assertEquals(NUMBER, actualAddress.getNumber());
    }

    @Test
    void deleteAddress() {

    }

    @Test
    void updateAddress() {
//
    }
}