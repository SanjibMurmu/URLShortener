package com.sanju.shortener.dto;

public class UrlRequest {
    private String realUrl;
    private String customAlias;

    public String getRealUrl() {
        return realUrl;
    }
    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }
    
    public String getCustomAlias() {
        return customAlias;
    }
    public void setCustomAlias(String customAlias) {
        this.customAlias = customAlias;
    }
}
