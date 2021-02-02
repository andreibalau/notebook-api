package com.app.notebook.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllerSmokeTest {

    private final UserSpaceController userSpaceController;
    private final FileController fileController;

    @Autowired
    public ControllerSmokeTest(UserSpaceController userSpaceController, FileController fileController) {
        this.userSpaceController = userSpaceController;
        this.fileController = fileController;
    }

    @Test
    void contextObjectInjectionTest() {
        assertThat(fileController).isNotNull();
        assertThat(userSpaceController).isNotNull();
    }
}
