package com.app.notebook.model.mapper;

import com.app.notebook.model.File;
import com.app.notebook.model.dto.UploadFileDto;
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
}
