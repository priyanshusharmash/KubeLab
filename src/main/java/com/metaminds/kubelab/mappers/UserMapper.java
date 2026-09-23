package com.metaminds.kubelab.mappers;

import com.metaminds.kubelab.dto.NewUserDto;
import com.metaminds.kubelab.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public User toUser(NewUserDto newUserDto, Long id) {
        return new User(
                id,
                newUserDto.username(),
                newUserDto.firstName(),
                newUserDto.lastName(),
                newUserDto.email(),
                newUserDto.address(),
                newUserDto.phoneNumber()
        );
    }
}
