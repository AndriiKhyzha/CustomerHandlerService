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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testCreateAddress() {
        // given
        Integer id = 73;
        Address mockedCreatedAddress = Address.builder()
                .dbId(id)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();
        when(addressService.createAddress(any(Address.class))).thenReturn(mockedCreatedAddress);

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
        assertEquals(mockedCreatedAddress.getDbId(), actualAddress.getDbId());
        assertEquals(mockedCreatedAddress.getCountry(), actualAddress.getCountry());
        assertEquals(mockedCreatedAddress.getCity(), actualAddress.getCity());
        assertEquals(mockedCreatedAddress.getStreet(), actualAddress.getStreet());
        assertEquals(mockedCreatedAddress.getNumber(), actualAddress.getNumber());
    }
    @Test
    void testDeleteAddress() {
        //given
        Integer id = 693;

        // when
        addressController.deleteAddress(id);

        // then
        verify(addressService, times(1)).deleteAddress(eq(id));
    }

    @Test
    void testUpdateAddress() {
        // given
        Integer id = 639;

        Address mockedAddressForUpdate = Address.builder()
                .dbId(id)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        when(addressService.updateAddress(any(Address.class))).thenReturn(mockedAddressForUpdate);

        Address requestAddress = Address.builder()
                .dbId(id)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        //when
        Address actualAddress = addressController.updateAddress(requestAddress);

        //then
        verify(addressService, times(1)).updateAddress(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();
        //
        assertEquals(id, capturedAddress.getDbId());
        assertEquals(COUNTRY, mockedAddressForUpdate.getCountry());
        assertEquals(CITY, mockedAddressForUpdate.getCity());
        assertEquals(STREET, mockedAddressForUpdate.getStreet());
        assertEquals(NUMBER, mockedAddressForUpdate.getNumber());

        //
        assertEquals(mockedAddressForUpdate.getDbId(), actualAddress.getDbId());
        assertEquals(mockedAddressForUpdate.getCountry(), actualAddress.getCountry());
        assertEquals(mockedAddressForUpdate.getCity(), actualAddress.getCity());
        assertEquals(mockedAddressForUpdate.getStreet(), actualAddress.getStreet());
        assertEquals(mockedAddressForUpdate.getNumber(), actualAddress.getNumber());
    }
}