package com.google.sps.models;

/**
 * Quote model to store a quote and its author.
 */
public class Quote {
    private String quoteText;
    private String author;

    public Quote() {}

    public Quote(String quoteText) {
        this.quoteText = quoteText;
    }

    public Quote(String quoteText, String author) {
        this.quoteText = quoteText;
        this.author = author;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return quoteText + " - " + author;
    }
}
