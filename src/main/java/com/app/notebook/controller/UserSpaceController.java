package com.app.notebook.controller;

import com.app.notebook.model.dto.UserSpaceDto;
import com.app.notebook.service.userspace.UserSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * notebook-api Created by Andrei on 12/12/2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/userspace")
public class UserSpaceController {

    private final UserSpaceService userSpaceService;

    @PostMapping
    public UserSpaceDto createUserSpace(@RequestBody UserSpaceDto userSpaceDto) {
        return userSpaceService.save(userSpaceDto);
    }
}
