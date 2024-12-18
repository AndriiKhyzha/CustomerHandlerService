package com.customer.handling.service.controller;

import com.customer.handling.service.api.model.AddressData;
import com.customer.handling.service.exception.RequestNotValidException;
import com.customer.handling.service.models.Address;
import com.customer.handling.service.service.AddressService;
import com.customer.handling.service.test.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
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
    void getAddress() {
        //given
        Integer id = 735;
        Address findedByIdAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/getAddress_foundByIdAddress.json");

        when(addressService.getAddress(eq(id))).thenReturn(findedByIdAddress);

        //when
        ResponseEntity<AddressData> actualAddress = addressController.getAddress(id);

        //then
        verify(addressService, times(1)).getAddress(eq(id));
        assertEquals(findedByIdAddress.getDbId(), actualAddress.getBody().getDbId());
        assertEquals(findedByIdAddress.getCountry(), actualAddress.getBody().getCountry());
        assertEquals(findedByIdAddress.getCity(), actualAddress.getBody().getCity());
        assertEquals(findedByIdAddress.getStreet(), actualAddress.getBody().getStreet());
        assertEquals(findedByIdAddress.getNumber(), actualAddress.getBody().getNumber());
    }

    @Test
    void getAddressWithZeroDbId() {
        assertThrows(RequestNotValidException.class,
                () -> addressController.getAddress(0),
                "DbId must not be Zero"
        );
        verify(addressService, never()).getAddress(any());
    }

    @Test
    void createAddress() {
        // given
        Address mockedCreatedAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/createAddress_mockedCreateAddress.json");

        when(addressService.createAddress(any(Address.class))).thenReturn(mockedCreatedAddress);

        AddressData requestAddress = TestUtils.readValue(this.getClass(), AddressData.class,
                "com/customer/handling/service/controller/address/createAddress_requestAddress.json");

        // when
        ResponseEntity<AddressData> actualAddress = addressController.createAddress(requestAddress);

        // then
        verify(addressService, times(1)).createAddress(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();

        assertNull(capturedAddress.getDbId());
        assertEquals(requestAddress.getCountry(), capturedAddress.getCountry());
        assertEquals(requestAddress.getCity(), capturedAddress.getCity());
        assertEquals(requestAddress.getStreet(), capturedAddress.getStreet());
        assertEquals(requestAddress.getNumber(), capturedAddress.getNumber());

        // assert returned created address in database
        assertEquals(mockedCreatedAddress.getDbId(), actualAddress.getBody().getDbId());
        assertEquals(mockedCreatedAddress.getCountry(), actualAddress.getBody().getCountry());
        assertEquals(mockedCreatedAddress.getCity(), actualAddress.getBody().getCity());
        assertEquals(mockedCreatedAddress.getStreet(), actualAddress.getBody().getStreet());
        assertEquals(mockedCreatedAddress.getNumber(), actualAddress.getBody().getNumber());
    }
    @Test
    void deleteAddress() {
        //given
        Integer id = 693;

        // when
        addressController.deleteAddress(id);

        // then
        verify(addressService, times(1)).deleteAddress(eq(id));
    }

    @Test
    void updateAddress() {
        // given
        Address mockedAddressForUpdate = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/controller/address/updateAddress_mockedAddressForUpdate.json");

        when(addressService.updateAddress(any(Address.class))).thenReturn(mockedAddressForUpdate);

        AddressData requestAddress = TestUtils.readValue(this.getClass(), AddressData.class,
                "com/customer/handling/service/controller/address/updateAddress_requestAddress.json");

        //when
        ResponseEntity<AddressData> actualAddress = addressController.updateAddress(requestAddress);

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
        assertEquals(mockedAddressForUpdate.getDbId(), actualAddress.getBody().getDbId());
        assertEquals(mockedAddressForUpdate.getCountry(), actualAddress.getBody().getCountry());
        assertEquals(mockedAddressForUpdate.getCity(), actualAddress.getBody().getCity());
        assertEquals(mockedAddressForUpdate.getStreet(), actualAddress.getBody().getStreet());
        assertEquals(mockedAddressForUpdate.getNumber(), actualAddress.getBody().getNumber());
    }
}