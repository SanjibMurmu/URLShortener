package com.sanju.shortener.repository;

import com.sanju.shortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortCode(String shortCode);
    UrlMapping findByRealUrl(String realUrl);
    boolean existsByShortCode(String shortCode);
    
}
