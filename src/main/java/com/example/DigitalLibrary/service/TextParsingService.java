package com.example.DigitalLibrary.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service to parse text content extracted from documents and extract structured data such as date, amount, and client.
 */
@Service
public class TextParsingService {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\b(\\d{2}[./-]\\d{2}[./-]\\d{4}|\\d{4}[./-]\\d{2}[./-]\\d{2})\\b");
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("\\b\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})\\b");
    private static final Pattern CLIENT_PATTERN = Pattern.compile("(?i)(?:Client|Klient)[:\\s]+(.+)", Pattern.UNICODE_CASE);

    /**
     * Parses the given text and extracts structured data fields.
     * @param text raw text content of the document
     * @return map of extracted field names to values
     */
    public Map<String, String> parse(String text) {
        Map<String, String> extracted = new HashMap<>();
        if (text == null || text.isEmpty()) {
            return extracted;
        }
        Matcher dateMatcher = DATE_PATTERN.matcher(text);
        if (dateMatcher.find()) {
            extracted.put("date", dateMatcher.group());
        }
        Matcher amountMatcher = AMOUNT_PATTERN.matcher(text);
        if (amountMatcher.find()) {
            extracted.put("amount", amountMatcher.group());
        }
        Matcher clientMatcher = CLIENT_PATTERN.matcher(text);
        if (clientMatcher.find()) {
            extracted.put("client", clientMatcher.group(1).trim());
        }
        return extracted;
    }
}