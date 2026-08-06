package com.example.setting_languages.model;

public class MailSettings {
    private String language;
    private int pageSize;
    private boolean spamFilterEnabled;
    private String signature;

    // Constructors
    public MailSettings() {
    }

    public MailSettings(String language, int pageSize, boolean spamFilterEnabled, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFilterEnabled = spamFilterEnabled;
        this.signature = signature;
    }

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpamFilterEnabled() {
        return spamFilterEnabled;
    }

    public void setSpamFilterEnabled(boolean spamFilterEnabled) {
        this.spamFilterEnabled = spamFilterEnabled;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
