package com.app.notebook.service;

import com.app.notebook.model.File;
import com.app.notebook.model.UserSpace;
import com.app.notebook.model.dto.FileDto;
import com.app.notebook.model.dto.FileRightDto;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.dto.UserSpaceDto;
import com.app.notebook.repository.FileRepository;
import com.app.notebook.repository.UserSpaceRepository;
import com.app.notebook.util.FileProcessor;
import com.app.notebook.service.file.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    private static final UserSpaceDto userSpaceDto = new UserSpaceDto(UUID.randomUUID(),null,null);
    private static final UploadFileDto uploadFileDto =  UploadFileDto.builder()
                                                                     .file("abc".getBytes(StandardCharsets.UTF_8))
                                                                     .name("test")
                                                                     .rights(Collections.<FileRightDto> emptyList())
                                                                     .userSpace(userSpaceDto)
                                                                     .build();
    @Mock
    private FileRepository fileRepository;
    @Mock
    private UserSpaceRepository userSpaceRepository;
    @Mock
    private FileProcessor fileProcessor;
    @InjectMocks
    private FileService fileService;

    @Test
    void Read_WhenFileNotExists_NoSuchElementExceptionIsThrown() {
        assertThrows(NoSuchElementException.class, () -> {
                fileService.read(null,null);
            });
    }

    @Test
    void Read_WhenFileExists_ProcessFileFromDisk() {
        Optional<File> file = Optional.of(File.builder()
                                              .path("file/path")
                                              .build());
        String value = "test";

        Mockito.when(fileRepository.findByUserSpace_IdAndName(any(),any()))
               .thenReturn(file);
        Mockito.when(fileProcessor.read(any()))
                .thenReturn(value.getBytes(StandardCharsets.UTF_8));

        FileDto response = fileService.read(any(),any());

        assertThat(response.getFile()).isNotNull();
        assertThat(response.getFile()).isEqualTo(value.getBytes(StandardCharsets.UTF_8));
        assertThat(response).isInstanceOf(FileDto.class);
    }

    @Test
    void Save_InvalidUserSpace_NoSuchElementExceptionThrown() {
        assertThrows(NoSuchElementException.class, () -> fileService.save(uploadFileDto));
    }

    @Test
    void Save_NullFileOnDisk_NullPointerExceptionThrown() {
        Optional<UserSpace> userSpace = Optional.of(UserSpace.builder()
                                                             .id(UUID.randomUUID())
                                                             .dirPath("invalid/dir/path")
                                                             .build());

        Mockito.when(userSpaceRepository.findById(any())).thenReturn(userSpace);
        Mockito.when(fileProcessor.save(any(),any(),any())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> fileService.save(uploadFileDto));
    }

}
