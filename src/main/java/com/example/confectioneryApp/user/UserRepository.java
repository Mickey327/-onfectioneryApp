package com.example.confectioneryApp.user;

import com.example.confectioneryApp.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u FROM users u LEFT JOIN FETCH u.reviewList reviewList  where u.id = :id")
    User findByIdFetch(@Param("id")  Long id);
}
