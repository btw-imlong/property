package com.example.PropertyRent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PropertyRent.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByFullName(String fullName);
}
