package com.example.trading_system_backend.cache;

public enum CacheType {

    CAPTCHA("captcha:"),

    ACCOUNT("account:"),

    ORDER("order:"),

    TRADE("trade:"),

    POSI("posi:"),
    ;

    private String type;

    CacheType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}
