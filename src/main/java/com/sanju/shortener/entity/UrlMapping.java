package com.sanju.shortener.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realUrl;
    private String shortCode;
    private int clickCount=0;
    private LocalDateTime lastClicked;
     public UrlMapping() {}
     public UrlMapping(String realUrl, String shortCode) {
        this.realUrl = realUrl;
        this.shortCode = shortCode;}

        public long getId() {return id;}
        public void setId(long id) {this.id = id;}

        public String getRealUrl() {return realUrl;}
        public void setRealUrl(String realUrl) {this.realUrl = realUrl;}

        public String getShortCode() {return shortCode;}
        public void setShortCode(String shortCode) {this.shortCode = shortCode;}

        public int getClickCount() {return clickCount;}
        public void setClickCount(int clickCount) {this.clickCount = clickCount;}

        public LocalDateTime getLastClicked() {return lastClicked;}
        public void setLastClicked(LocalDateTime lastClicked) {this.lastClicked = lastClicked;}
}
