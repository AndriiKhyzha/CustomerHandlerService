package com.customer.handling.service.apimodel;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer dbId;
    private String name;
    private Address address;
}

