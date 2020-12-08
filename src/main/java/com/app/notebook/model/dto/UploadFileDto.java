package com.app.notebook.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UploadFileDto {

    @NotBlank
    private UUID id;
    @NotBlank
    private byte[] file;
    @NotBlank
    private String name;
    @NotNull
    private UserSpaceDto space;
    private List<FileRightDto> rights;
}
