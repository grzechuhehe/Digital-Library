package com.example.DigitalLibrary;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.Binary;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class PDFImporter {
    public static void main(String[] args) throws IOException {
        String basePathStr = "C:/Users/huber/Desktop/archive/CompanyDocuments";
        Path basePath = Paths.get(basePathStr);
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("digital_library");

        // znajdź wszystkie katalogi zawierające PDF-y
        Set<Path> pdfDirectories = Files.walk(basePath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().toLowerCase().endsWith(".pdf"))
                .map(Path::getParent)
                .collect(Collectors.toSet());

        for (Path directory : pdfDirectories) {
            String collectionName = directory.getFileName().toString();
            MongoCollection<Document> collection = db.getCollection(collectionName);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.pdf")) {
                for (Path pdfFile : stream) {
                    try {
                        byte[] data = Files.readAllBytes(pdfFile);
                        Path relative = basePath.relativize(pdfFile);
                        Document doc = new Document("fileName", pdfFile.getFileName().toString())
                                .append("relativePath", relative.toString())
                                .append("fullPath", pdfFile.toAbsolutePath().toString())
                                .append("size", data.length)
                                .append("uploaded", new Date())
                                .append("content", new Binary(data));
                        collection.insertOne(doc);
                        System.out.println("[" + collectionName + "] Dodano: " + pdfFile.getFileName());
                    } catch (IOException e) {
                        System.err.println("Błąd pliku: " + pdfFile);
                        e.printStackTrace();
                    }
                }
            }
        }

        mongoClient.close();
    }
}



