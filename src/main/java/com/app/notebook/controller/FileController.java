package com.app.notebook.controller;

import com.app.notebook.model.dto.FileDto;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

/**
 * notebook-api Created by Catalin on 10/21/2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/files")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public FileDto getFile(@RequestParam UUID userSpaceId, @RequestParam String fileName) throws IOException {
        return fileService.read(userSpaceId, fileName);
    }

    @PostMapping
    public FileDto save(@RequestBody UploadFileDto fileDto) throws IOException {
        return fileService.save(fileDto);
    }
}
