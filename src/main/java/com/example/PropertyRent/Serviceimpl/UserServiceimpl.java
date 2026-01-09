package com.example.PropertyRent.Serviceimpl;


import com.example.PropertyRent.DtoRequest.BecomeAgent;
import com.example.PropertyRent.Entity.RoleType;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Repositories.UserRepository;
import com.example.PropertyRent.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User becomeAgent(BecomeAgent request) {
        // Find user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Only allow USER role
        if (user.getRole() != RoleType.USER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only USER can become AGENT");
        }

        // Validate requested roleId
        if (!request.getRoleId().equals(2L)) {  // 2 = AGENT
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only become AGENT role");
        }

        // Promote
        user.setRole(RoleType.AGENT);

        return userRepository.save(user);
    }
}
