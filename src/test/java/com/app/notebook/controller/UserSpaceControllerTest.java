package com.app.notebook.controller;

import com.app.notebook.model.dto.UserDto;
import com.app.notebook.model.dto.UserSpaceDto;
import com.app.notebook.service.userspace.UserSpaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserSpaceControllerTest {
    private static final UUID userSpaceId = UUID.randomUUID();
    private static final String userSpaceName = "space";
    @Mock
    private UserSpaceService userSpaceService;
    @InjectMocks
    private UserSpaceController userSpaceController;
    @Captor
    private ArgumentCaptor<UserSpaceDto> userSpaceDtoCaptor;

    @Test
    void Create_ThenSavedObjectIsReturned(){
        Mockito.when(userSpaceService.save(any()))
               .thenReturn(UserSpaceDto.builder()
                                       .id(userSpaceId)
                                       .name(userSpaceName)
                                       .user(new UserDto())
                                       .build());

        UserSpaceDto response = userSpaceController.create(any());

        assertThat(response.getId()).isEqualTo(userSpaceId);
        assertThat(response.getName()).isEqualTo(userSpaceName);
        assertThat(response.getUser()).isInstanceOf(UserDto.class);
    }

    @Test
    void Create_CaptureObject_CheckObjectIntegrity() throws IOException {
        UserSpaceDto userSpaceDto = UserSpaceDto.builder()
                                                .id(userSpaceId)
                                                .name(userSpaceName)
                                                .user(new UserDto())
                                                .build();

        userSpaceController.create(userSpaceDto);

        Mockito.verify(userSpaceService).save(userSpaceDtoCaptor.capture());

        UserSpaceDto userSpaceDtoCaptured = userSpaceDtoCaptor.getValue();

        assertThat(userSpaceDtoCaptured.getId()).isEqualTo(userSpaceId);
        assertThat(userSpaceDtoCaptured.getName()).isEqualTo(userSpaceName);
        assertThat(userSpaceDtoCaptured.getUser()).isInstanceOf(UserDto.class);
    }
}
