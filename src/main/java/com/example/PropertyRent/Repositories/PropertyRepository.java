package com.example.PropertyRent.Repositories;

import com.example.PropertyRent.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByIdAndAgentId(Long id, Long agentId);
}
