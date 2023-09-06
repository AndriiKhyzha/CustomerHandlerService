package com.customer.handling.service.services;

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
    private static final String COUNTRY = "Ukraine";
    private static final String CITY = "Myrhorod";
    private static final String STREET = "Komyshnianska";
    private static final String NUMBER = "133";

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Captor
    private ArgumentCaptor<AddressDB> addressDBArgumentCaptor;

    @Test
    void getAddress() {
        //given
        AddressDB mockedGetAddressDB = AddressDB.builder()
                .id(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();
        when(addressRepository.findById(eq(ID))).thenReturn(Optional.of(mockedGetAddressDB));

        //when
        Address actualAddress = addressServiceImpl.getAddress(ID);

        //then
        verify(addressRepository).findById(eq(ID));

        assertEquals(ID, actualAddress.getDbId());
        assertEquals(COUNTRY, actualAddress.getCountry());
        assertEquals(CITY, actualAddress.getCity());
        assertEquals(STREET, actualAddress.getStreet());
        assertEquals(NUMBER, actualAddress.getNumber());
    }

    @Test
    void createAddress() {
        //given
        Address addressToCreate = Address.builder()
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        AddressDB createdAddress = AddressDB.builder()
                .id(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();
        when(addressRepository.save(any(AddressDB.class))).thenReturn(createdAddress);

        //when
        Address actualAddress = addressServiceImpl.createAddress(addressToCreate);

        //then
        verify(addressRepository).save(addressDBArgumentCaptor.capture());
        //check captured values in DB save method
        AddressDB capturedAddressDB = addressDBArgumentCaptor.getValue();
        assertNull(capturedAddressDB.getId());
        assertEquals(createdAddress.getCountry(), capturedAddressDB.getCountry());
        assertEquals(createdAddress.getCity(), capturedAddressDB.getCity());
        assertEquals(createdAddress.getStreet(), capturedAddressDB.getStreet());
        assertEquals(createdAddress.getNumber(), capturedAddressDB.getNumber());

        //comparing createdDB Object values with actual tested method return Object values
        assertEquals(createdAddress.getId(), actualAddress.getDbId());
        assertEquals(createdAddress.getCountry(), actualAddress.getCountry());
        assertEquals(createdAddress.getCity(), actualAddress.getCity());
        assertEquals(createdAddress.getStreet(), actualAddress.getStreet());
        assertEquals(createdAddress.getNumber(), actualAddress.getNumber());
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
        String countryBefore = "Germany";
        String cityBefore = "Berlin";
        String streetBefore = "strasse";
        String numberBefore = "369";

        AddressDB addressDBBefore = AddressDB.builder()
                .id(ID)
                .country(countryBefore)
                .city(cityBefore)
                .street(streetBefore)
                .number(numberBefore)
                .build();

        when(addressRepository.findById(eq(ID))).thenReturn(Optional.of(addressDBBefore));

        AddressDB updatedAddress = AddressDB.builder()
                .id(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();
        when(addressRepository.save(any(AddressDB.class))).thenReturn(updatedAddress);

        Address addressToUpdate = Address.builder()
                .dbId(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        //when
        Address actualAddress =  addressServiceImpl.updateAddress(addressToUpdate);

        //then
        verify(addressRepository).findById(eq(ID));

        verify(addressRepository).save(addressDBArgumentCaptor.capture());
        AddressDB capturedAddressDB = addressDBArgumentCaptor.getValue();
        //check values in Object for updating
        assertEquals(ID, capturedAddressDB.getId());
        assertEquals(COUNTRY, capturedAddressDB.getCountry());
        assertEquals(CITY, capturedAddressDB.getCity());
        assertEquals(STREET, capturedAddressDB.getStreet());
        assertEquals(NUMBER, capturedAddressDB.getNumber());

        //check actual address with was updated
        assertEquals(addressToUpdate.getDbId(), actualAddress.getDbId());
        assertEquals(addressToUpdate.getCountry(), actualAddress.getCountry());
        assertEquals(addressToUpdate.getCity(), actualAddress.getCity());
        assertEquals(addressToUpdate.getStreet(), actualAddress.getStreet());
        assertEquals(addressToUpdate.getNumber(), actualAddress.getNumber());
    }
}