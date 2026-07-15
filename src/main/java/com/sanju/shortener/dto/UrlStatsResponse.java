package com.sanju.shortener.dto;

import java.time.LocalDateTime;

public class UrlStatsResponse {
    private long clickCount;
    private LocalDateTime lastClicked;

    public UrlStatsResponse(long clickCount, LocalDateTime lastClicked) {
        this.clickCount = clickCount;
        this.lastClicked = lastClicked;
    }

    public long getClickCount() {
        return clickCount;
    }

    public LocalDateTime getLastClicked() {
        return lastClicked;
    }
}
