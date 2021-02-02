package com.app.notebook.controller;

import com.app.notebook.model.dto.FileDto;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.dto.UserSpaceDto;
import com.app.notebook.service.file.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FileControllerTest {
    private static final UUID userSpaceId = UUID.randomUUID();
    private static final String fileName = "test";
    private static final byte[] fileBytes = "abc".getBytes(StandardCharsets.UTF_8);
    @Mock
    private FileService fileService;
    @InjectMocks
    private FileController fileController;
    @Captor
    ArgumentCaptor<String> fileNameCaptor;
    @Captor
    ArgumentCaptor<UUID> userSpaceIdCaptor;
    @Captor
    ArgumentCaptor<UploadFileDto> uploadFileDtoCaptor;

    @Test
    void GetFile_RetrieveObject_CheckObjectIntegrity() throws Exception {
        Mockito.when(fileService.read(userSpaceId, fileName))
                .thenReturn(FileDto.builder()
                        .file(fileBytes)
                        .name(fileName)
                        .userSpace(new UserSpaceDto())
                        .build());

        FileDto response = fileController.getFile(userSpaceId, fileName);

        assertThat(response.getFile()).isEqualTo(fileBytes);
        assertThat(response.getName()).isEqualTo(fileName);
        assertThat(response.getUserSpace()).isInstanceOf(UserSpaceDto.class);
    }

    @Test
    void GetFile_CaptureParamsSent_CheckParamsIntegrity() throws IOException {
        fileController.getFile(userSpaceId, fileName);

        Mockito.verify(fileService).read(userSpaceIdCaptor.capture(),fileNameCaptor.capture());

        UUID userSpaceIdCaptured = userSpaceIdCaptor.getValue();
        String fileNameCaptured = fileNameCaptor.getValue();

        assertThat(userSpaceIdCaptured).isEqualTo(userSpaceId);
        assertThat(fileNameCaptured).isEqualTo(fileName);
    }

    @Test
    void Save_ThenSavedObjectIsReturned() throws IOException {
        Mockito.when(fileService.save(any()))
                .thenReturn(FileDto.builder()
                        .file(fileBytes)
                        .name(fileName)
                        .userSpace(new UserSpaceDto())
                        .build());

        FileDto response = fileController.save(any());

        assertThat(response.getFile()).isEqualTo(fileBytes);
        assertThat(response.getName()).isEqualTo(fileName);
        assertThat(response.getUserSpace()).isInstanceOf(UserSpaceDto.class);
    }

    @Test
    void Save_CaptureObject_CheckObjectIntegrity() throws IOException {
        UploadFileDto uploadFileDto = UploadFileDto.builder()
                                           .file(fileBytes)
                                           .name(fileName)
                                           .userSpace(new UserSpaceDto())
                                           .build();

        fileController.save(uploadFileDto);

        Mockito.verify(fileService).save(uploadFileDtoCaptor.capture());

        UploadFileDto uploadFileDtoCaptured = uploadFileDtoCaptor.getValue();

        assertThat(uploadFileDtoCaptured.getFile()).isEqualTo(fileBytes);
        assertThat(uploadFileDtoCaptured.getName()).isEqualTo(fileName);
        assertThat(uploadFileDtoCaptured.getUserSpace()).isInstanceOf(UserSpaceDto.class);
    }
}
