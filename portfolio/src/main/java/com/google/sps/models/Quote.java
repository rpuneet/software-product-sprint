package com.google.sps.models;

/**
 * Quote model to store a quote and its author.
 */
public class Quote {
    private String quoteText;
    private String author;
    private final String ANONYMOUS = "Anonymous";

    public Quote() {
        this.quoteText = "";
        this.author = ANONYMOUS;
    }

    public Quote(String quoteText) {
        this.quoteText = quoteText;
        this.author = ANONYMOUS;
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

    public static class Validator {
        private static boolean isEmptyOrWhiteSpace(String text) {
            return text == null || text.trim().isEmpty();
        }

        public static ValidationResponse validate(Quote quote) {
            ValidationResponse validationResponse = new ValidationResponse(true);

            if (isEmptyOrWhiteSpace(quote.getQuoteText())) {
                validationResponse.setValid(false);
            }
            if (isEmptyOrWhiteSpace(quote.getAuthor())) {
                validationResponse.setValid(false);
            }
            return validationResponse;
        }
    }
}
