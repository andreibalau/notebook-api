package com.app.notebook.service.file;

import com.app.notebook.model.File;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.mapper.Mapper;
import com.app.notebook.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final Mapper mapper;
    private int fileNameIndex = 0;

    public File save(UploadFileDto userFile) throws IOException {
        java.io.File fileStored = saveToDisk(userFile);
        File file = mapper.map(userFile);
        file.setPath(fileStored.getPath());
        if (fileRepository.existsBySpaceAndName(file.getSpace(), file.getName())) {
            file.setName(duplicateFileNameSolver(file));
        }

        return fileRepository.save(file);
    }

    private java.io.File saveToDisk(UploadFileDto userFile) throws IOException {
        UUID id = UUID.randomUUID();
        java.io.File file = new java.io.File(id.toString() + ".txt");
        file.createNewFile();
        if (!file.canWrite()) {
            throw new IOException("Oops, can't write to this file!");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String source = new String(userFile.getFile(), "UTF-8");
        writer.write(source);
        writer.flush();
        writer.close();

        return file;
    }

    private String duplicateFileNameSolver(File file) {
        fileNameIndex++;
        String fileName = String.format("%s(%s)", file.getName(), fileNameIndex);
        if (!fileRepository.existsBySpaceAndName(file.getSpace(), fileName)) {
            fileNameIndex = 0;
            return fileName;
        }
        return duplicateFileNameSolver(file);
    }
}
