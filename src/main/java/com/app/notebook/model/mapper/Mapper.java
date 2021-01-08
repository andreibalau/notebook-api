package com.app.notebook.model.mapper;

import com.app.notebook.model.File;
import com.app.notebook.model.UserSpace;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.dto.UserSpaceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final static ModelMapper mapper = new ModelMapper();

    private Mapper() {
    }

    public static File map(UploadFileDto uploadFileDto) {
        return mapper.map(uploadFileDto, File.class);
    }

    public static UserSpace map(UserSpaceDto userSpaceDto) {
        return mapper.map(userSpaceDto, UserSpace.class);
    }

    public static UserSpaceDto map(UserSpace userSpace) {
        return mapper.map(userSpace, UserSpaceDto.class);
    }
}
