package com.app.notebook.service.file;

import com.app.notebook.model.File;
import com.app.notebook.model.UserSpace;
import com.app.notebook.model.dto.FileDto;
import com.app.notebook.model.dto.UploadFileDto;
import com.app.notebook.model.mapper.Mapper;
import com.app.notebook.repository.FileRepository;
import com.app.notebook.repository.UserSpaceRepository;
import com.app.notebook.util.FileProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserSpaceRepository userSpaceRepository;
    private final FileProcessor localDiskFileProcessor;
    private int fileNameIndex = 0;

    public FileDto read(UUID userSpaceId, String fileName){
        File file = fileRepository.findByUserSpace_IdAndName(userSpaceId, fileName).orElseThrow(NoSuchElementException::new);
        String filePath = file.getPath();

        FileDto response = Mapper.map(file);
        response.setFile(localDiskFileProcessor.read(filePath));

        return response;
    }

    public FileDto save(UploadFileDto userFileDto){
        File file = Mapper.map(userFileDto);
        UUID id = UUID.randomUUID();

        java.io.File localFile = saveToDisk(file, id);
        preConstructFile(file, localFile, id);

        file = fileRepository.save(file);
        file.setFile(userFileDto.getFile());
        FileDto response = Mapper.map(file);

        return response;
    }

    private java.io.File saveToDisk(File file, UUID id){
        String dirPath = getDirPath(file.getUserSpace());
        return localDiskFileProcessor.save(dirPath, id.toString(), file.getFile());
    }

    private void preConstructFile(File file,java.io.File localFile, UUID id){
        file.setId(id);
        file.setPath(localFile.getPath());

        UserSpace userSpace = file.getUserSpace();
        String fileName = file.getName();

        if (fileRepository.existsByUserSpaceAndName(userSpace, fileName)) {
            file.setName(duplicateNameSolver(userSpace,fileName));
        }
    }

    private String getDirPath(UserSpace userSpace){
        UserSpace userSpaceRetrieved = userSpaceRepository.findById(userSpace.getId()).orElseThrow(NoSuchElementException::new);
        return userSpaceRetrieved.getDirPath();
    }

    private String duplicateNameSolver(UserSpace userSpace, String fileName) {
        fileNameIndex++;
        String fileNameGenerated = String.format("%s(%s)", fileName, fileNameIndex);
        if (!fileRepository.existsByUserSpaceAndName(userSpace, fileNameGenerated)) {
            fileNameIndex = 0;
            return fileNameGenerated;
        }
        return duplicateNameSolver(userSpace, fileName);
    }

}
