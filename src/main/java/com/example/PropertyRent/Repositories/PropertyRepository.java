package com.example.PropertyRent.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PropertyRent.Entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
