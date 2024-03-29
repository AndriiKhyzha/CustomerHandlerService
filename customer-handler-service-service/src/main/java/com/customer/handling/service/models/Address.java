package com.customer.handling.service.models;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Integer dbId;
    private String country;
    private String city;
    private String street;
    private String number;
}
