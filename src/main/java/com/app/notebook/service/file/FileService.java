package com.app.notebook.service.file;

import com.app.notebook.model.File;
import com.app.notebook.model.UserSpace;
import com.app.notebook.model.dto.FileDto;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.mapper.Mapper;
import com.app.notebook.repository.FileRepository;
import com.app.notebook.repository.UserSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserSpaceRepository userSpaceRepository;
    private int fileNameIndex = 0;

    public FileDto read(UUID userSpaceId, String fileName) throws IOException {
        File file = fileRepository.findByUserSpace_IdAndName(userSpaceId, fileName).orElseThrow(NoSuchElementException::new);
        String filePath = file.getPath();

        FileDto response = Mapper.map(file);
        response.setFile(readFromDisk(filePath));

        return response;
    }


    public FileDto save(UploadFileDto userFileDto) throws IOException {
        UUID id = UUID.randomUUID();
        java.io.File fileStored = saveToDisk(userFileDto, id);

        File file = Mapper.map(userFileDto);
        file.setId(id);
        file.setPath(fileStored.getPath());

        if (fileRepository.existsByUserSpaceAndName(file.getUserSpace(), file.getName())) {
            file.setName(duplicateFileNameSolver(file));
        }

        file = fileRepository.save(file);
        FileDto response = Mapper.map(file);
        response.setFile(userFileDto.getFile());

        return response;
    }

    private byte[] readFromDisk(String filePath) throws IOException {
        return FileUtil.readAsByteArray(new java.io.File(filePath));
    }

    private java.io.File saveToDisk(UploadFileDto userFileDto, UUID fileName) throws IOException {
        UserSpace userSpace = userSpaceRepository.findById(userFileDto.getUserSpace().getId()).orElseThrow(NoSuchElementException::new);
        String dirPath = userSpace.getDirPath();

        java.io.File file = new java.io.File(dirPath + "/" + fileName.toString() + ".txt");
        file.createNewFile();
        if (!file.canWrite()) {
            throw new IOException("Oops, can't write to this file!");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String source = new String(userFileDto.getFile(), "UTF-8");
        writer.write(source);
        writer.flush();
        writer.close();

        return file;
    }

    private String duplicateFileNameSolver(File file) {
        fileNameIndex++;
        String fileName = String.format("%s(%s)", file.getName(), fileNameIndex);
        if (!fileRepository.existsByUserSpaceAndName(file.getUserSpace(), fileName)) {
            fileNameIndex = 0;
            return fileName;
        }
        return duplicateFileNameSolver(file);
    }
}
