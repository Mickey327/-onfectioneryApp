package com.example.confectioneryApp.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT c FROM cart c LEFT JOIN FETCH c.cartItemList cartItemList where c.id = :id")
    Cart findByIdFetch(@Param("id")  Long id);
}
