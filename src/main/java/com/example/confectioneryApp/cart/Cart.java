package com.example.confectioneryApp.cart;

import com.example.confectioneryApp.user.User;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cart")
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ElementCollection()
    @CollectionTable(
            name = "cartitem",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id")
    )
    private List<CartItem> cartItemList;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
