package com.customer.handling.service.database;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @JoinColumn(name = "address")
    @OneToOne(cascade = CascadeType.ALL)
    private AddressDB address;
}
