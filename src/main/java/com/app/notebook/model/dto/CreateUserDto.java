package com.app.notebook.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/** notebook-api Created by Catalin on 10/21/2020 */
@Getter
@Setter
public class CreateUserDto {
  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @Email @NotBlank private String email;
  @NotBlank private String password;
}
