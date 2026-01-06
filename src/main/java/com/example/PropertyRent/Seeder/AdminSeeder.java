package com.example.PropertyRent.Seeder;

import com.example.PropertyRent.Entity.Role;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Repositories.RoleRepository;
import com.example.PropertyRent.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.existsByEmail("admin@example.com")) {
            return; // already exists
        }

        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
        User admin = new User();
        admin.setFullName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPhone("0123456789");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(adminRole);

        userRepository.save(admin);

        System.out.println("✅ Admin user seeded successfully");
    }
}
