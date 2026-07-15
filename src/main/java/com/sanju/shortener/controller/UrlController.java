package com.sanju.shortener.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.net.URI;

import com.sanju.shortener.dto.UrlRequest;
import com.sanju.shortener.dto.UrlStatsResponse;
import com.sanju.shortener.entity.UrlMapping;
import com.sanju.shortener.service.UrlService;


@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/short")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest urlRequest) {
        try {
            String shortUrl = urlService.shortenUrl(urlRequest.getRealUrl(), urlRequest.getCustomAlias());
            return ResponseEntity.ok("sanju.io/"+shortUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        System.out.println("--- DEBUGGING REDIRECT ---");
        System.out.println("1. Browser clicked on short code: [" + shortCode + "]");

        String originalUrl = urlService.getOriginalUrl(shortCode);
        System.out.println("2. Database found original URL: [" + originalUrl + "]");
        if (originalUrl != null) {
            System.out.println("3. Success! Redirecting browser now.");
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
        } else {
            System.out.println("3. FAIL! Sending 404 because the URL was not in the database.");
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlStatsResponse> getStats(@PathVariable String shortCode) {
        UrlMapping mapping = urlService.findByShortCode(shortCode); 
        if (mapping == null) return ResponseEntity.notFound().build();
    
        return ResponseEntity.ok(new UrlStatsResponse(mapping.getClickCount(),mapping.getLastClicked()));
}
    
    
}

