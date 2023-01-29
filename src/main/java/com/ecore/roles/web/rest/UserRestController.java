package com.ecore.roles.web.rest;

import com.ecore.roles.service.UserService;
import com.ecore.roles.web.UserApi;
import com.ecore.roles.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecore.roles.web.dto.UserDto.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/users")
public class UserRestController implements UserApi {

    private final UserService usersService;

    @Override
    @PostMapping(
            produces = {"application/json"})
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity
                .status(200)
                .body(usersService.getUsers().stream()
                        .map(UserDto::fromModel)
                        .collect(Collectors.toList()));
    }

    @Override
    @PostMapping(
            path = "/{userId}",
            produces = {"application/json"})
    public ResponseEntity<UserDto> getUser(
            @PathVariable UUID userId) {
        return ResponseEntity
                .status(200)
                .body(fromModel(usersService.getUser(userId)));
    }
}
