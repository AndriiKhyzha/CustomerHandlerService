package com.customer.handling.service.mapping;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.service.api.CustomerData;
import com.customer.handling.service.models.Address;
import com.customer.handling.service.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper

public interface ApiControllerModelMapper {

@Mappings({
        @Mapping(target = "dbId", source = "customer.dbId"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "address", source = "address")}
)
    CustomerData map(Customer customer);

    @Mappings({
            @Mapping(target = "dbId", source = "customerData.dbId"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "address", source = "address")
    })
    Customer map(CustomerData customerData);

    @Mappings({
            @Mapping(target = "dbId", source = "address.dbId"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "street", source = "street"),
            @Mapping(target = "number", source = "number")
    })

    AddressData map(Address address);

    @Mappings({
            @Mapping(target = "dbId", source = "addressData.dbId"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "street", source = "street"),
            @Mapping(target = "number", source = "number")
})

    Address map(AddressData addressData);
}
