package com.google.sps.dao;

import com.google.sps.models.Quote;
import com.google.sps.models.ValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class QuoteDao implements IQuoteDao{
    private List<Quote> quoteList;

    public QuoteDao() {
        quoteList = new ArrayList<>();
    }

    @Override
    public Quote getRandomQuote() {
        return quoteList.get((int) (Math.random() * quoteList.size()));
    }

    @Override
    public ValidationResponse addQuote(Quote quote) {
        ValidationResponse validationResponse = Quote.Validator.validate(quote);

        if (!validationResponse.isValid()) {
            return validationResponse;
        }
        quoteList.add(quote);
        return validationResponse;
    }
}
