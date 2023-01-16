package com.tcs.assignment.springbootassignment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserModel {
    private String userName;
    @NotNull
    @NotBlank
    private String firstname;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String password;
}
