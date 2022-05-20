package com.example.confectioneryApp.order;

import com.example.confectioneryApp.cart.Cart;
import lombok.*;

import javax.persistence.*;

@Entity(name = "orders")
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "additional_address")
    private String additionalAddress;
    @Column(name = "additional_info")
    private String additionalInfo;
}
