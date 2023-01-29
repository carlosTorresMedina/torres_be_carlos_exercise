package com.ecore.roles.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.ecore.roles.client.UserRestClient;
import com.ecore.roles.client.model.User;
import com.ecore.roles.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRestClient userRestClient;

    public User getUser(UUID userId) {
        return userRestClient.getUser(userId).getBody();
    }

    public List<User> getUsers() {
        return userRestClient.getUsers().getBody();
    }
}
