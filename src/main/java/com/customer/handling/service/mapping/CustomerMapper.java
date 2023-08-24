package com.customer.handling.service.mapping;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.CustomerDB;
import org.mapstruct.*;

@Mapper
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "id", source = "dbId"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "street", source = "street"),
            @Mapping(target = "number", source = "number")
    })
    AddressDB mapToAddressDB(Address address);

    @Mappings({
            @Mapping(target = "dbId", source = "id"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "street", source = "street"),
            @Mapping(target = "number", source = "number")
    })
    Address mapToAddress(AddressDB addressDB);

    @Mappings({
            @Mapping(target = "id" , source = "addressDB.id"),
            @Mapping(target = "country" , ignore = true),
            @Mapping(target = "city" , ignore = true),
            @Mapping(target = "street" , ignore = true),
            @Mapping(target = "number" , ignore = true)
    })
    AddressDB mapToAddressDB(AddressDB addressDB, Address address);

    @AfterMapping
    default void mapAddressDBAfterMapping(@MappingTarget AddressDB.AddressDBBuilder addressDBBuilder,
                                          AddressDB addressDB, Address address) {
        addressDBBuilder.country(address.getCountry() == null || address.getCountry().isEmpty()
                ? addressDB.getCountry() : address.getCountry());
        addressDBBuilder.city(address.getCity() == null || address.getCity().isEmpty()
                ? addressDB.getCity() : address.getCity());
        addressDBBuilder.street(addressDB.getStreet() == null || address.getStreet().isEmpty()
                ? addressDB.getStreet() : address.getStreet());
        addressDBBuilder.number(address.getNumber() == null || address.getNumber().isEmpty()
                ? addressDB.getNumber() : address.getNumber());
    }


   @Mappings({
           @Mapping(target = "id", source = "dbId"),
           @Mapping(target = "name", source = "customer.name")
   })
    CustomerDB mapToCustomerDB(Customer customer);

    @Mappings({
            @Mapping(target = "dbId", source = "customerDB.id"),
            @Mapping(target = "name", source = "customerDB.name")
    })
    Customer mapToCustomer(CustomerDB customerDB);

    @Mappings({
            @Mapping(target = "id", source = "customerDB.id"),
            @Mapping(target = "name", ignore = true)
    })
    CustomerDB mapToCustomerDb(CustomerDB customerDB, Customer customer);

    @AfterMapping
    default void mapCustomerDBAfterMapping(@MappingTarget CustomerDB.CustomerDBBuilder customerDBBuilder,
                                        CustomerDB customerDB, Customer customer) {
        customerDBBuilder.name(customer.getName() == null || customer.getName().isEmpty()
                ? customerDB.getName() : customer.getName()
        );
    }
}
