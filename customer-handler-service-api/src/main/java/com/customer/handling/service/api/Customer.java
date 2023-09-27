package com.customer.handling.service.api;

import lombok.*;

@ToString
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

