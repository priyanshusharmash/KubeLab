package com.metaminds.kubelab.controllers;

import com.metaminds.kubelab.dto.NewUserDto;
import com.metaminds.kubelab.entities.User;
import com.metaminds.kubelab.exceptions.UserNotFoundException;
import com.metaminds.kubelab.mappers.UserMapper;
import com.metaminds.kubelab.responses.NewUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private List<User> users = new ArrayList<>();
    private Long count = 0L;

    private Long getCount() {
        return ++count;
    }

    @PostMapping
    public ResponseEntity<NewUserResponse> createUser(
            @Valid @RequestBody NewUserDto newUserDto
    ) {
        Long id = getCount();
        users.add(userMapper.toUser(newUserDto, id));
        return new ResponseEntity<>(new NewUserResponse(id, newUserDto.username()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id
    ) {
        User user = users.stream()
                .filter(user1 -> Objects.equals(user1.getId(), id)).findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody NewUserDto newUserDto
    ) {
        User user = users.stream()
                .filter(user1 -> Objects.equals(user1.getId(), id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setUsername(newUserDto.username());
        user.setFirstName(newUserDto.firstName());
        user.setLastName(newUserDto.lastName());
        user.setEmail(newUserDto.email());
        user.setAddress(newUserDto.address());
        user.setPhoneNumber(newUserDto.phoneNumber());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable Long id
    ) {
        User user = users.stream()
                .filter(user1 -> Objects.equals(user1.getId(), id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        boolean remove = users.removeIf(user2 -> user2.getId().equals(id));
        if(remove) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else throw new RuntimeException();
    }
}
