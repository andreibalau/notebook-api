package com.app.notebook.service;

import com.app.notebook.service.file.FileService;
import com.app.notebook.service.userspace.UserSpaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceSmokeTest {
    private final UserSpaceService userSpaceService;
    private final FileService fileService;

    @Autowired
    public ServiceSmokeTest(UserSpaceService userSpaceService, FileService fileService) {
        this.userSpaceService = userSpaceService;
        this.fileService = fileService;
    }

    @Test
    void contextObjectInjectionTest() {
        assertThat(userSpaceService).isNotNull();
        assertThat(fileService).isNotNull();
    }
}
