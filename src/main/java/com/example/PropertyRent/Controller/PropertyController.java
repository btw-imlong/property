package com.example.PropertyRent.Controller;

import com.example.PropertyRent.Dto.ApiResponse;

import com.example.PropertyRent.Dto.PropertyResponse;
import com.example.PropertyRent.DtoRequest.PropertyRequest;
import com.example.PropertyRent.Security.JwtUtil;
import com.example.PropertyRent.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ApiResponse<PropertyResponse> create(
            @RequestBody PropertyRequest request,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtUtil.extractEmail(token);
        return new ApiResponse<>(true, "Property created",
                propertyService.create(request, email));
    }

    @PutMapping("/{id}")
    public ApiResponse<PropertyResponse> update(
            @PathVariable Long id,
            @RequestBody PropertyRequest request,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtUtil.extractEmail(token);
        return new ApiResponse<>(true, "Property updated",
                propertyService.update(id, request, email));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token
    ) {
        String email = jwtUtil.extractEmail(token);
        propertyService.delete(id, email);
        return new ApiResponse<>(true, "Property deleted", null);
    }

    @GetMapping("/{id}")
    public ApiResponse<PropertyResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Success", propertyService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<PropertyResponse>> getAll() {
        return new ApiResponse<>(true, "Success", propertyService.getAll());
    }
}
