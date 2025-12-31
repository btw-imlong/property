package com.example.PropertyRent.Seeder;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.PropertyRent.Entity.Role;
import com.example.PropertyRent.Repositories.RoleRepository;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        List<String> roles = List.of("ADMIN", "AGENT", "USER");

        for (String roleName : roles) {
            roleRepository.findByName(roleName)
                    .orElseGet(() -> roleRepository.save(new Role(null, roleName)));
        }

        System.out.println("✅ Roles seeded successfully");
    }
}
