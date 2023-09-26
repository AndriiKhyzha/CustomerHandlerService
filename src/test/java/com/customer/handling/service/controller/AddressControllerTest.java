package com.customer.handling.service.controller;

import com.customer.handling.service.TestUtils;
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
        Address findedByIdAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/getAddress_foundByIdAddress.json");

        when(addressService.getAddress(eq(id))).thenReturn(findedByIdAddress);

        //when
        Address actualAddress = addressController.getAddress(id);

        //then
        verify(addressService, times(1)).getAddress(eq(id));
        assertEquals(findedByIdAddress.getCountry(), actualAddress.getCountry());
        assertEquals(findedByIdAddress.getCity(), actualAddress.getCity());
        assertEquals(findedByIdAddress.getStreet(), actualAddress.getStreet());
        assertEquals(findedByIdAddress.getNumber(), actualAddress.getNumber());
    }

    @Test
    void testCreateAddress() {
        // given
        Address mockedCreatedAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/createAddress_mockedCreateAddress.json");
        when(addressService.createAddress(any(Address.class))).thenReturn(mockedCreatedAddress);

        Address requestAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/createAddress_requestAddress.json");

        // when
        Address actualAddress = addressController.createAddress(requestAddress);

        // then
        verify(addressService, times(1)).createAddress(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();

        assertNull(capturedAddress.getDbId());
        assertEquals(requestAddress.getCountry(), capturedAddress.getCountry());
        assertEquals(requestAddress.getCity(), capturedAddress.getCity());
        assertEquals(requestAddress.getStreet(), capturedAddress.getStreet());
        assertEquals(requestAddress.getNumber(), capturedAddress.getNumber());

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
        Address mockedAddressForUpdate = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/updateAddress_mockedAddressForUpdate.json");

        when(addressService.updateAddress(any(Address.class))).thenReturn(mockedAddressForUpdate);

        Address requestAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/updateAddress_requestAddress.json");

        //when
        Address actualAddress = addressController.updateAddress(requestAddress);

        //then
        verify(addressService, times(1)).updateAddress(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();
        //
        assertEquals(requestAddress.getDbId(), capturedAddress.getDbId());
        assertEquals(requestAddress.getCountry(), capturedAddress.getCountry());
        assertEquals(requestAddress.getCity(), capturedAddress.getCity());
        assertEquals(requestAddress.getStreet(), capturedAddress.getStreet());
        assertEquals(requestAddress.getNumber(), capturedAddress.getNumber());

        //
        assertEquals(mockedAddressForUpdate.getDbId(), actualAddress.getDbId());
        assertEquals(mockedAddressForUpdate.getCountry(), actualAddress.getCountry());
        assertEquals(mockedAddressForUpdate.getCity(), actualAddress.getCity());
        assertEquals(mockedAddressForUpdate.getStreet(), actualAddress.getStreet());
        assertEquals(mockedAddressForUpdate.getNumber(), actualAddress.getNumber());
    }
}