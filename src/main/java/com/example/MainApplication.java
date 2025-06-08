package com.example;

import com.example.DigitalLibrary.service.FileLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class MainApplication {

    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader();

        try {
            Map<String, byte[]> files = fileLoader.loadFilesFromArchive();

            // Iterowanie przez wszystkie odczytane pliki
            for (Map.Entry<String, byte[]> entry : files.entrySet()) {
                System.out.println("File Name: " + entry.getKey());
                System.out.println("Byte Array: " + Arrays.toString(entry.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

