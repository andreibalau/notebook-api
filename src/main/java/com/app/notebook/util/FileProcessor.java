package com.app.notebook.util;

public interface FileProcessor {

    byte[] read(String filePath);

    java.io.File save(String dirPath, String fileName, byte[] fileSource);

}
