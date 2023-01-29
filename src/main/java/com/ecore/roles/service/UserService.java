package com.ecore.roles.service;

import java.util.List;
import java.util.UUID;
import com.ecore.roles.client.model.User;

public interface UserService {

    User getUser(UUID id);

    List<User> getUsers();
}
