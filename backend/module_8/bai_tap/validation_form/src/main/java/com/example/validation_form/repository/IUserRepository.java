package com.example.validation_form.repository;

import com.example.validation_form.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("select count(u) > 0 from User u where u.email = :email")
    boolean existsByEmail(String email);
}
