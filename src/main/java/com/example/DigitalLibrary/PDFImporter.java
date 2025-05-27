package com.example.DigitalLibrary;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.Binary;

import java.io.*;
import java.nio.file.*;
import java.util.Date;

public class PDFImporter {
    public static void main(String[] args) throws IOException {
        String basePath = "C:/Users/huber/Desktop/archive/CompanyDocuments";
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("companyDocs");
        MongoCollection<Document> collection = db.getCollection("pdfFiles");

        Path base = Paths.get(basePath);
        Files.walk(base)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().toLowerCase().endsWith(".pdf"))
                .forEach(path -> {
                    try {
                        byte[] data = Files.readAllBytes(path);
                        Path relative = base.relativize(path);
                        Document doc = new Document("fileName", path.getFileName().toString())
                                .append("relativePath", relative.toString())
                                .append("fullPath", path.toAbsolutePath().toString())
                                .append("size", data.length)
                                .append("type", detectType(path))
                                .append("uploaded", new Date())
                                .append("content", new Binary(data));
                        collection.insertOne(doc);
                        System.out.println("Wgrano: " + path.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        mongoClient.close();
    }

    private static String detectType(Path path) {
        String parent = path.getParent().getFileName().toString().toLowerCase();
        if (parent.contains("invoice")) return "Invoice";
        if (parent.contains("report")) return "Report";
        if (parent.contains("order")) return "Order";
        return "Unknown";
    }
}
