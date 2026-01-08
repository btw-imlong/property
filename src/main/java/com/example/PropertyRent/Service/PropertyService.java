package com.example.PropertyRent.Service;


import com.example.PropertyRent.Dto.PropertyResponse;
import com.example.PropertyRent.DtoRequest.PropertyRequest;

import java.util.List;

public interface PropertyService {

    PropertyResponse create(PropertyRequest request, String email);

    PropertyResponse update(Long id, PropertyRequest request, String email);

    void delete(Long id, String email);

    PropertyResponse getById(Long id);

    List<PropertyResponse> getAll();
}
