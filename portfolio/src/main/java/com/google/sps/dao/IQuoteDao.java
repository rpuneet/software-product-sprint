package com.google.sps.dao;

import com.google.sps.models.Quote;
import com.google.sps.models.ValidationResponse;

/**
 * Quote data access object interface to access quotes.
 */
public interface IQuoteDao {
    /**
     * @return Quote A random quote from a list of quotes.
     */
    Quote getRandomQuote();

    /**
     * @param quote Quote to add in the list of quotes.
     * @return ValidationResponse add the quote to the list if the code is valid.
     */
    ValidationResponse addQuote(Quote quote);
}
