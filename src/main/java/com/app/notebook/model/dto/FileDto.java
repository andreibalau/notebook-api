package com.app.notebook.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    @NotBlank
    private byte[] file;
    @NotBlank
    private String name;
    @NotNull
    private UserSpaceDto userSpace;
}
