package com.metaminds.kubelab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewUserDto(
        @NotBlank(message = "Username can't be blank")
        String username,
        @NotBlank(message = "Firstname can't be blank")
        String firstName,
        String lastName,
        @NotBlank(message = "Email can't be blank")
        @Email(message = "Enter a valid email address")
        String email,
        String address,
        String phoneNumber
) {
}
