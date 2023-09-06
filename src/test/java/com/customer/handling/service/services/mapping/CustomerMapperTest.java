package com.customer.handling.service.services.mapping;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.CustomerDB;
import com.customer.handling.service.mapping.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerMapperTest {

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private final Integer ID = 13;
    private final String NAME = "Parker";
    private final String COUNTRY = "Ukraine";
    private final String CITY = "Kharkiv";
    private final String STREET = "Luhanska";
    private final String NUMBER = "133";

    @Test
    void mapToAddressDB() {
        Address mappedAddress = Address.builder()
                .dbId(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        AddressDB actualAddressDB = customerMapper.mapToAddressDB(mappedAddress);

        assertEquals(ID, actualAddressDB.getId());
        assertEquals(COUNTRY, actualAddressDB.getCountry());
        assertEquals(CITY, actualAddressDB.getCity());
        assertEquals(STREET, actualAddressDB.getStreet());
        assertEquals(NUMBER, actualAddressDB.getNumber());
    }

    @Test
    void mapToAddress() {
        AddressDB mappedAddressDB = AddressDB.builder()
                .id(ID)
                .country(COUNTRY)
                .city(CITY)
                .street(STREET)
                .number(NUMBER)
                .build();

        Address actualAddress = customerMapper.mapToAddress(mappedAddressDB);

        assertEquals(ID, actualAddress.getDbId());
        assertEquals(COUNTRY, actualAddress.getCountry());
        assertEquals(CITY, actualAddress.getCity());
        assertEquals(STREET, actualAddress.getStreet());
        assertEquals(NUMBER, actualAddress.getNumber());
    }

    @ParameterizedTest
    @MethodSource
    void mapToAddressDB_twoSources(
            String inputCountry, String extractedCountry, String expectedCountry,
            String inputCity, String extractedCity, String expectedCity,
            String inputStreet, String extractedStreet, String expectedStreet,
            String inputNumber, String extractedNumber, String expectedNumber
    ) {
        Address inputAddress = Address.builder()
                .dbId(ID)
                .country(inputCountry)
                .city(inputCity)
                .street(inputStreet)
                .number(inputNumber)
                .build();

        AddressDB extractedAddressDB = AddressDB.builder()
                .id(ID)
                .country(extractedCountry)
                .city(extractedCity)
                .street(extractedStreet)
                .number(extractedNumber)
                .build();

        AddressDB actualMappedAddressDB = customerMapper.mapToAddressDB(extractedAddressDB, inputAddress);

        assertEquals(ID, actualMappedAddressDB.getId());
        assertEquals(expectedCountry, actualMappedAddressDB.getCountry());
        assertEquals(expectedCity, actualMappedAddressDB.getCity());
        assertEquals(expectedStreet, actualMappedAddressDB.getStreet());
        assertEquals(expectedNumber, actualMappedAddressDB.getNumber());
    }

    private static Stream<Arguments> mapToAddressDB_twoSources() {
        return Stream.of(
                Arguments.of(
                        "Ukraine", "USA", "Ukraine",
                        "Kyiv", "Washington", "Kyiv",
                        "Nezalezna", "Washington str.", "Nezalezna",
                        "13", "23", "13"
                ),
                Arguments.of(
                        null, "USA", "USA",
                        null, "Washington", "Washington",
                        null, "Washington str.", "Washington str.",
                        null, "23", "23"
                ),
                Arguments.of(
                        "", "USA", "USA",
                        "", "Washington", "Washington",
                        "", "Washington str.", "Washington str.",
                        "", "23", "23"
                ),
                Arguments.of(
                        " ", "USA", "USA",
                        " ", "Washington", "Washington",
                        " ", "Washington str.", "Washington str.",
                        " ", "23", "23"
                )
        );
    }

    @Test
    void mapToCustomerDB() {
        Customer customer = Customer.builder()
                .dbId(ID)
                .name(NAME)
                .build();

        CustomerDB actualCustomerDB = customerMapper.mapToCustomerDB(customer);

        assertEquals(ID, actualCustomerDB.getId());
        assertEquals(NAME, actualCustomerDB.getName());
    }

    @Test
    void mapToCustomer() {
        CustomerDB customerDB = CustomerDB.builder()
                .id(ID)
                .name(NAME)
                .build();
         Customer actualCustomer = customerMapper.mapToCustomer(customerDB);

         assertEquals(ID, actualCustomer.getDbId());
         assertEquals(NAME, actualCustomer.getName());
    }

    @ParameterizedTest
    @MethodSource
    void mapToCustomerDb_twoSources(
            String inputCustomerName,
            String extractedCustomerDBName,
            String expectedMappedName
    ) {
        Customer customer = Customer.builder()
                .dbId(ID)
                .name(inputCustomerName)
                .build();

        CustomerDB customerDb = CustomerDB.builder()
                .id(ID)
                .name(extractedCustomerDBName)
                .build();

        CustomerDB actualCustomerDB = customerMapper.mapToCustomerDb(customerDb, customer);

        assertEquals(ID, actualCustomerDB.getId());
        assertEquals(expectedMappedName, actualCustomerDB.getName());
    }

    private static Stream<Arguments> mapToCustomerDb_twoSources() {
        return Stream.of(
                Arguments.of("Yarema", "Rebel", "Yarema"),
                Arguments.of(null, "Rebel", "Rebel"),
                Arguments.of("", "Rebel", "Rebel"),
                Arguments.of(" ", "Rebel", "Rebel")
        );
    }
}