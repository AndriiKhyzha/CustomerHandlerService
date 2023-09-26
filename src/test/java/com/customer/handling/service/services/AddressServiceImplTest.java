package com.customer.handling.service.services;

import com.customer.handling.service.TestUtils;
import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.repository.AddressRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private static final Integer ID = 13;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Captor
    private ArgumentCaptor<AddressDB> addressDBArgumentCaptor;

    @Test
    void getAddress() {
        //given
        AddressDB mockedGetAddressDB = TestUtils.readValue(this.getClass(), AddressDB.class,
                "com/customer/handling/service/services/address/getAddress_mockedGetAddressDB.json");
        when(addressRepository.findById(eq(ID))).thenReturn(Optional.of(mockedGetAddressDB));

        //when
        Address actualAddress = addressServiceImpl.getAddress(ID);

        //then
        verify(addressRepository).findById(eq(ID));

        assertEquals(mockedGetAddressDB.getId(), actualAddress.getDbId());
        assertEquals(mockedGetAddressDB.getCountry(), actualAddress.getCountry());
        assertEquals(mockedGetAddressDB.getCity(), actualAddress.getCity());
        assertEquals(mockedGetAddressDB.getStreet(), actualAddress.getStreet());
        assertEquals(mockedGetAddressDB.getNumber(), actualAddress.getNumber());
    }

    @Test
    void createAddress() {
        //given
        Address addressToCreate = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/services/address/createAddress_addressToCreate.json");

        AddressDB createdAddressDB = TestUtils.readValue(this.getClass(), AddressDB.class,
                "com/customer/handling/service/services/address/createAddress_createdAddressDB.json");

        when(addressRepository.save(any(AddressDB.class))).thenReturn(createdAddressDB);
        //when
        Address actualAddress = addressServiceImpl.createAddress(addressToCreate);
        //then
        verify(addressRepository).save(addressDBArgumentCaptor.capture());
        //check captured values in DB save method
        AddressDB capturedAddressDB = addressDBArgumentCaptor.getValue();
        assertNull(capturedAddressDB.getId());
        assertEquals(createdAddressDB.getCountry(), capturedAddressDB.getCountry());
        assertEquals(createdAddressDB.getCity(), capturedAddressDB.getCity());
        assertEquals(createdAddressDB.getStreet(), capturedAddressDB.getStreet());
        assertEquals(createdAddressDB.getNumber(), capturedAddressDB.getNumber());

        //comparing createdDB Object values with actual tested method return Object values
        assertEquals(createdAddressDB.getId(), actualAddress.getDbId());
        assertEquals(createdAddressDB.getCountry(), actualAddress.getCountry());
        assertEquals(createdAddressDB.getCity(), actualAddress.getCity());
        assertEquals(createdAddressDB.getStreet(), actualAddress.getStreet());
        assertEquals(createdAddressDB.getNumber(), actualAddress.getNumber());
    }

    @Test
    void deleteAddress() {
        //when
        addressServiceImpl.deleteAddress(ID);
        //then
        verify(addressRepository).deleteById(eq(ID));
    }

    @Test
    void updateAddress() {
        //given
        AddressDB addressDBBefore = TestUtils.readValue(this.getClass(), AddressDB.class,
                "com/customer/handling/service/services/address/updateAddress_addressDBBefore.json");

        when(addressRepository.findById(eq(ID))).thenReturn(Optional.of(addressDBBefore));

        AddressDB updatedAddressDB = TestUtils.readValue(this.getClass(), AddressDB.class,
                "com/customer/handling/service/services/address/updateAddress_updatedAddressDB.json");
        when(addressRepository.save(any(AddressDB.class))).thenReturn(updatedAddressDB);

        Address addressToUpdate = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/services/address/updateAddress_addressToUpdate.json");

        //when
        Address actualAddress =  addressServiceImpl.updateAddress(addressToUpdate);

        //then
        verify(addressRepository).findById(eq(ID));

        verify(addressRepository).save(addressDBArgumentCaptor.capture());
        AddressDB capturedAddressDB = addressDBArgumentCaptor.getValue();
        //check values in Object for updating
        assertEquals(updatedAddressDB.getId(), capturedAddressDB.getId());
        assertEquals(updatedAddressDB.getCountry(), capturedAddressDB.getCountry());
        assertEquals(updatedAddressDB.getCity(), capturedAddressDB.getCity());
        assertEquals(updatedAddressDB.getStreet(), capturedAddressDB.getStreet());
        assertEquals(updatedAddressDB.getNumber(), capturedAddressDB.getNumber());

        //check actual address with was updated
        assertEquals(addressToUpdate.getDbId(), actualAddress.getDbId());
        assertEquals(addressToUpdate.getCountry(), actualAddress.getCountry());
        assertEquals(addressToUpdate.getCity(), actualAddress.getCity());
        assertEquals(addressToUpdate.getStreet(), actualAddress.getStreet());
        assertEquals(addressToUpdate.getNumber(), actualAddress.getNumber());
    }
}