package com.app.notebook.model.dto;

import com.app.notebook.model.AccessType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRightDto {

    @NotNull
    private UserDto user;
    @NotNull
    private AccessType type;
}
