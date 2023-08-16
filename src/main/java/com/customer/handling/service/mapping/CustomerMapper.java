package com.customer.handling.service.mapping;

import com.customer.handling.service.apimodel.Address;
import com.customer.handling.service.apimodel.Customer;
import com.customer.handling.service.database.AddressDB;
import com.customer.handling.service.database.CustomerDB;
import org.mapstruct.*;

@Mapper
public interface CustomerMapper {

    @Mapping(target = "id", source = "dbId")
    AddressDB mapToAddress(Address address);

    @Mapping(target = "dbId", source = "id")
    Address mapToAddressDB(AddressDB addressDB);

    @Mapping(target = "id", source = "dbId")
    CustomerDB mapToCustomerDB(Customer customer);

    @Mappings({
            @Mapping(target = "dbId", source = "id")
    })
    Customer mapToCustomer(CustomerDB customerDB);

    @Mappings({
            @Mapping(target = "id", source = "customerDB.id"),
            @Mapping(target = "name", ignore = true)
    })
    CustomerDB mapToCustomerDb(CustomerDB customerDB, Customer customer);

    @AfterMapping
    default  void mapCustomerDBAfterMapping(@MappingTarget CustomerDB.CustomerDBBuilder customerDBBuilder,
                                        CustomerDB customerDB, Customer customer) {
        customerDBBuilder.name(customer.getName() == null || customer.getName().isEmpty()
                ? customerDB.getName() : customer.getName()
        );
    }
}
