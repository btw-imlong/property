package com.example.PropertyRent.Serviceimpl;


import com.example.PropertyRent.Dto.PropertyResponse;
import com.example.PropertyRent.DtoRequest.PropertyRequest;
import com.example.PropertyRent.Entity.Property;
import com.example.PropertyRent.Entity.RoleType;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Repositories.PropertyRepository;
import com.example.PropertyRent.Repositories.UserRepository;
import com.example.PropertyRent.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public PropertyResponse create(PropertyRequest request, String email) {

        User user = getUser(email);

        if (user.getRole() != RoleType.AGENT) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only agent can create property");
        }

        Property property = mapToEntity(request);
        property.setAgentId(user.getId());

        propertyRepository.save(property);

        return mapToResponse(property);
    }

    @Override
    public PropertyResponse update(Long id, PropertyRequest request, String email) {

        User user = getUser(email);

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        if (user.getRole() != RoleType.AGENT || !property.getAgentId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not owner of this property");
        }

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setAddress(request.getAddress());
        property.setPrice(request.getPrice());
        property.setElectricityCost(request.getElectricityCost());
        property.setWaterCost(request.getWaterCost());

        propertyRepository.save(property);

        return mapToResponse(property);
    }

    @Override
    public void delete(Long id, String email) {

        User user = getUser(email);

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        boolean isOwner = property.getAgentId().equals(user.getId());
        boolean isAdmin = user.getRole() == RoleType.ADMIN;

        if (!isOwner && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No permission to delete");
        }

        propertyRepository.delete(property);
    }

    @Override
    public PropertyResponse getById(Long id) {

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));

        return mapToResponse(property);
    }

    @Override
    public List<PropertyResponse> getAll() {
        return propertyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ================= HELPERS =================

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    private Property mapToEntity(PropertyRequest r) {
        Property p = new Property();
        p.setTitle(r.getTitle());
        p.setDescription(r.getDescription());
        p.setAddress(r.getAddress());
        p.setPrice(r.getPrice());
        p.setElectricityCost(r.getElectricityCost());
        p.setWaterCost(r.getWaterCost());
        return p;
    }

    private PropertyResponse mapToResponse(Property p) {
        return new PropertyResponse(
                p.getId(),
                p.getTitle(),
                p.getAddress(),
                p.getPrice(),
                p.getAgentId()
        );
    }
}
