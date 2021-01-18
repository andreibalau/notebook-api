package com.app.notebook.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSpaceDto {

    @NotBlank
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private UserDto user;
}
