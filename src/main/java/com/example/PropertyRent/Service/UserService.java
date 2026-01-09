package com.example.PropertyRent.Service;

import com.example.PropertyRent.DtoRequest.BecomeAgent;
import com.example.PropertyRent.Entity.User;


public interface UserService {
    User becomeAgent(BecomeAgent request);
}
