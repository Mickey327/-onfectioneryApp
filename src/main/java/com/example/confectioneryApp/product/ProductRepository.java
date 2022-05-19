package com.example.confectioneryApp.product;

import com.example.confectioneryApp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    @Query(value = "SELECT p FROM product p LEFT JOIN FETCH p.reviewList reviewList  where p.id = :id")
    Product findByIdFetch(@Param("id")  Long id);
}
