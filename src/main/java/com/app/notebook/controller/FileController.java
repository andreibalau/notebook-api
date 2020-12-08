package com.app.notebook.controller;

import com.app.notebook.model.File;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * notebook-api Created by Catalin on 10/21/2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/files")
public class FileController {

    private final FileService fileService;

    @PostMapping
    public File save(@RequestBody UploadFileDto fileDto) throws IOException {
        return fileService.save(fileDto);
    }
}
