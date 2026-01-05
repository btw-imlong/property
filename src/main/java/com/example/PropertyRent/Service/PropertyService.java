package com.example.PropertyRent.Service;

import com.example.PropertyRent.Entity.Property;
import com.example.PropertyRent.Repositories.PropertyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }
}
