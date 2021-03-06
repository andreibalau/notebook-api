package com.app.notebook.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {

    @NotBlank
    private byte[] file;
    @NotBlank
    private String name;
    @NotNull
    private UserSpaceDto userSpace;
    private List<FileRightDto> rights;
}
