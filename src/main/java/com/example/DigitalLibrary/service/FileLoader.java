package com.example.DigitalLibrary.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileLoader {

    public Map<String, byte[]> loadFilesFromArchive() throws IOException {
        Map<String, byte[]> fileMap = new HashMap<>();
        Path archivePath = Paths.get("src/main/resources/archive");

        try (var paths = Files.walk(archivePath)) {
            paths.filter(Files::isRegularFile).forEach(filePath -> {
                try {
                    byte[] fileContent = Files.readAllBytes(filePath);
                    fileMap.put(filePath.getFileName().toString(), fileContent);
                } catch (IOException e) {
                    System.err.println("Failed to read file: " + filePath + " - " + e.getMessage());
                }
            });
        }

        return fileMap;
    }
}
