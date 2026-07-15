package com.sanju.shortener.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.sanju.shortener.entity.UrlMapping;
import com.sanju.shortener.repository.UrlRepository;

@Service
public class UrlService {
    private final UrlRepository urlRepo;
    private static final String BASE_62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public UrlService(UrlRepository urlRepo) {
        this.urlRepo = urlRepo;
    }

    public String shortenUrl(String originalUrl, String customAlias) {
        if (originalUrl == null || !originalUrl.startsWith("http")) {
           throw new IllegalArgumentException("Invalid URL: Must start with http or https");}

        if (originalUrl.contains("sanju.io")) {
            throw new IllegalArgumentException("Cannot shorten a link that already belongs to this service.");}

        
    
        if (customAlias != null && !customAlias.trim().isEmpty()) {
            String sanitizedAlias = customAlias.toLowerCase().trim(); // Enforce lowercase[cite: 6]
            if (urlRepo.existsByShortCode(sanitizedAlias)) {
                throw new IllegalArgumentException("Custom alias already exists.");
        }
            UrlMapping urlMapping = new UrlMapping(originalUrl, sanitizedAlias); // Use mapping constructor[cite: 4]
            urlRepo.save(urlMapping);
            return sanitizedAlias;
    }

        UrlMapping existingMapping = urlRepo.findByRealUrl(originalUrl);
        if (existingMapping != null) {
            return existingMapping.getShortCode();
    }

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setRealUrl(originalUrl);
        urlMapping = urlRepo.save(urlMapping);
        
        String shortUrl = encodeBase62(urlMapping.getId());

        urlMapping.setShortCode(shortUrl);
        urlRepo.save(urlMapping);

        return shortUrl;
    }

    private String encodeBase62(long id) {
        if (id == 0) return String.valueOf(BASE_62_ALPHABET.charAt(0));
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            int remainder = (int) (id % 62);
            shortUrl.append(BASE_62_ALPHABET.charAt(remainder));
            id /= 62;
        }
        return shortUrl.reverse().toString();
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping urlMapping = urlRepo.findByShortCode(shortCode);
        if (urlMapping != null) {
            urlMapping.setClickCount(urlMapping.getClickCount() + 1);
            urlMapping.setLastClicked(LocalDateTime.now());
            urlRepo.save(urlMapping);
            return urlMapping.getRealUrl();
        }
        return null;
    }

    public int getClickCount(String shortCode) {
        UrlMapping urlMapping = urlRepo.findByShortCode(shortCode);
        if (urlMapping != null) {
            return urlMapping.getClickCount();
        }
        return 0;
    }

    public UrlMapping findByShortCode(String shortCode) {
        return urlRepo.findByShortCode(shortCode);
    }

}
