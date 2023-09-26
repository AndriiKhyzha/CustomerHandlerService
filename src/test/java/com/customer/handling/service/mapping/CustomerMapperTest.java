package com.customer.handling.service.mapping;

import com.customer.handling.service.TestUtils;
import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.CustomerDB;
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

    @Test
    void mapToAddressDB() {
        Address mappedAddress = TestUtils.readValue(this.getClass(), Address.class,
                "com/customer/handling/service/mapping/mapToAddressDB_mappedAddress.json");

        AddressDB actualAddressDB = customerMapper.mapToAddressDB(mappedAddress);

        assertEquals(mappedAddress.getDbId(), actualAddressDB.getId());
        assertEquals(mappedAddress.getCountry(), actualAddressDB.getCountry());
        assertEquals(mappedAddress.getCity(), actualAddressDB.getCity());
        assertEquals(mappedAddress.getStreet(), actualAddressDB.getStreet());
        assertEquals(mappedAddress.getNumber(), actualAddressDB.getNumber());
    }

    @Test
    void mapToAddress() {
        AddressDB mappedAddressDB = TestUtils.readValue(this.getClass(), AddressDB.class,
                "com/customer/handling/service/mapping/mapToAddress_mappedAddressDB.json");

        Address actualAddress = customerMapper.mapToAddress(mappedAddressDB);

        assertEquals(mappedAddressDB.getId(), actualAddress.getDbId());
        assertEquals(mappedAddressDB.getCountry(), actualAddress.getCountry());
        assertEquals(mappedAddressDB.getCity(), actualAddress.getCity());
        assertEquals(mappedAddressDB.getStreet(), actualAddress.getStreet());
        assertEquals(mappedAddressDB.getNumber(), actualAddress.getNumber());
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
        Customer mappedCustomer = TestUtils.readValue(this.getClass(), Customer.class,
                "com/customer/handling/service/mapping/mapToCustomerDB_mappedCustomer.json");

        CustomerDB actualCustomerDB = customerMapper.mapToCustomerDB(mappedCustomer);

        assertEquals(mappedCustomer.getDbId(), actualCustomerDB.getId());
        assertEquals(mappedCustomer.getName(), actualCustomerDB.getName());
    }

    @Test
    void mapToCustomer() {
        CustomerDB customerDB = TestUtils.readValue(this.getClass(), CustomerDB.class,
                "com/customer/handling/service/mapping/mapToCustomer_customerDB.json");

         Customer actualCustomer = customerMapper.mapToCustomer(customerDB);

         assertEquals(customerDB.getId(), actualCustomer.getDbId());
         assertEquals(customerDB.getName(), actualCustomer.getName());
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