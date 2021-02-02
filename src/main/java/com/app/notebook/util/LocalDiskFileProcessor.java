package com.app.notebook.util;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LocalDiskFileProcessor implements FileProcessor{

    @SneakyThrows
    public byte[] read(String filePath) {
        return FileUtil.readAsByteArray(new java.io.File(filePath));
    }

    @SneakyThrows
    public java.io.File save(String dirPath, String fileName, byte[] fileSource) {
        File file = new java.io.File(dirPath + "/" + fileName + ".txt");
        file.createNewFile();

        if (!file.canWrite()) {
            throw new IOException("Can't write to the new file : " + file.getPath());
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String source = new String(fileSource, "UTF-8");
        writer.write(source);
        writer.flush();
        writer.close();

        return file;
    }

}
