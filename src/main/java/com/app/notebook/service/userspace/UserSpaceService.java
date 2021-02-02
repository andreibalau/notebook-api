package com.app.notebook.service.userspace;

import com.app.notebook.exception.UnexpectedUserSpaceException;
import com.app.notebook.model.UserSpace;
import com.app.notebook.model.dto.UserSpaceDto;
import com.app.notebook.model.mapper.Mapper;
import com.app.notebook.repository.UserSpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.rmi.UnexpectedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSpaceService {//TODO: reformat code as in fileservice

    private final UserSpaceRepository userSpaceRepository;

    @SneakyThrows
    public UserSpaceDto save(UserSpaceDto userSpaceDto) {
        UserSpace newUserSpace = Mapper.map(userSpaceDto);
        processNewUserSpace(newUserSpace);

        return Mapper.map(userSpaceRepository.save(newUserSpace));
    }

    private void processNewUserSpace(UserSpace userSpace) throws UnexpectedException {
        UUID id = UUID.randomUUID();
        File theDir = new File(String.format("files/%s/%s", userSpace.getUser().getId(), id));//pattern: files/<user-id>/<user-space>
        if (!theDir.exists()) {
            theDir.mkdirs();
            userSpace.setId(id);
            userSpace.setDirPath(theDir.getPath());
        } else {
            throw new UnexpectedUserSpaceException(String.format("Unexpected space for user: %s , space already exists on disk with the following path: %s",
                    userSpace.getUser().getId(),
                    theDir.getPath()));
        }
    }
}
