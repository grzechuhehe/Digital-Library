package com.example.DigitalLibrary.service;

import com.example.DigitalLibrary.model.BaseRecord;
import com.example.DigitalLibrary.model.InventoryReport;
import com.example.DigitalLibrary.model.Invoice;
import com.example.DigitalLibrary.model.Monthly;
import com.example.DigitalLibrary.model.MonthlyCategory;
import com.example.DigitalLibrary.model.PurchaseOrder;
import com.example.DigitalLibrary.model.ShippingOrder;
import com.example.DigitalLibrary.model.StockReport;
import com.example.DigitalLibrary.pdf.PDFProcessor;
import com.example.DigitalLibrary.repository.InventoryReportRepository;
import com.example.DigitalLibrary.repository.InvoiceRepository;
import com.example.DigitalLibrary.repository.MonthlyCategoryRepository;
import com.example.DigitalLibrary.repository.MonthlyRepository;
import com.example.DigitalLibrary.repository.PurchaseOrderRepository;
import com.example.DigitalLibrary.repository.ShippingOrderRepository;
import com.example.DigitalLibrary.repository.StockReportRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Service to import PDF documents from a configured archive directory.
 */
@Service
public class DocumentImportService implements CommandLineRunner {

    @Value("${archive.path:src/main/resources/archive}")
    private String archivePath;

    private final PDFProcessor pdfProcessor = new PDFProcessor();

    private final InvoiceRepository invoiceRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ShippingOrderRepository shippingOrderRepository;
    private final InventoryReportRepository inventoryReportRepository;
    private final StockReportRepository stockReportRepository;
    private final MonthlyRepository monthlyRepository;
    private final MonthlyCategoryRepository monthlyCategoryRepository;

    public DocumentImportService(InvoiceRepository invoiceRepository,
                                 PurchaseOrderRepository purchaseOrderRepository,
                                 ShippingOrderRepository shippingOrderRepository,
                                 InventoryReportRepository inventoryReportRepository,
                                 StockReportRepository stockReportRepository,
                                 MonthlyRepository monthlyRepository,
                                 MonthlyCategoryRepository monthlyCategoryRepository) {
        this.invoiceRepository = invoiceRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.shippingOrderRepository = shippingOrderRepository;
        this.inventoryReportRepository = inventoryReportRepository;
        this.stockReportRepository = stockReportRepository;
        this.monthlyRepository = monthlyRepository;
        this.monthlyCategoryRepository = monthlyCategoryRepository;
    }

    @Override
    public void run(String... args) {
        importDocuments();
    }

    /**
     * Walks the archive directory, processes PDF files, and saves records.
     */
    public void importDocuments() {
        Path base = Paths.get(archivePath);
        if (!Files.exists(base)) {
            System.err.println("Archive path does not exist: " + archivePath);
            return;
        }
        try (Stream<Path> paths = Files.walk(base)) {
            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    byte[] bytes = Files.readAllBytes(path);
                    if (!pdfProcessor.isPDF(bytes)) {
                        return;
                    }
                    String text = pdfProcessor.extractTextFromPDF(bytes);
                    String relative = base.relativize(path).toString();
                    String full = path.toAbsolutePath().toString();
                    Binary content = new Binary(bytes);
                    int size = bytes.length;
                    Date uploaded = new Date();

                    String type = determineType(path, base);
                    switch (type) {
                        case "Invoices":
                            Invoice invoice = new Invoice();
                            populateBaseFields(invoice, path.getFileName().toString(), relative, full, size, uploaded, content, text);
                            invoiceRepository.save(invoice);
                            break;
                        case "PurchaseOrders":
                            PurchaseOrder po = new PurchaseOrder();
                            populateBaseFields(po, path.getFileName().toString(), relative, full, size, uploaded, content, text);
                            purchaseOrderRepository.save(po);
                            break;
                        case "ShippingOrders":
                            ShippingOrder so = new ShippingOrder();
                            populateBaseFields(so, path.getFileName().toString(), relative, full, size, uploaded, content, text);
                            shippingOrderRepository.save(so);
                            break;
                        case "InventoryReport":
                            InventoryReport ir = new InventoryReport();
                            populateBaseFields(ir, path.getFileName().toString(), relative, full, size, uploaded, content, text);
                            inventoryReportRepository.save(ir);
                            break;
                        default:
                            System.err.println("Unknown document type for path: " + path);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to import file: " + path + " - " + e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Error walking archive path: " + e.getMessage());
        }
    }

    /**
     * Determines document type based on the file path relative to the archive base.
     */
    private String determineType(Path path, Path base) {
        Path rel = base.relativize(path);
        if (rel.getNameCount() > 1 && rel.getName(0).equals("CompanyDocuments")) {
            return rel.getName(1).toString();
        }
        return rel.getName(0).toString();
    }

    private void populateBaseFields(BaseRecord record,
                                    String fileName,
                                    String relativePath,
                                    String fullPath,
                                    int size,
                                    Date uploaded,
                                    Binary content,
                                    String text) {
        record.setFileName(fileName);
        record.setRelativePath(relativePath);
        record.setFullPath(fullPath);
        record.setSize(size);
        record.setUploaded(uploaded);
        record.setContent(content);
        record.setTextContent(text);
    }
}