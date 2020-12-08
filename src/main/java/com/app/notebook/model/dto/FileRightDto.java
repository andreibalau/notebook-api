package com.app.notebook.model.dto;

import com.app.notebook.model.AccessType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class FileRightDto {

    @NotNull
    private UserDto user;
    @NotNull
    private AccessType type;
}
