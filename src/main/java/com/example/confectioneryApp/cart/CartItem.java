package com.example.confectioneryApp.cart;

import com.example.confectioneryApp.product.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItem {
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public double countPrice(){
        return this.product.getPrice() * quantity;
    }
}
