package com.google.sps.dao;

import com.google.sps.models.Quote;

/**
 * Quote data access object interface to access quotes.
 */
public interface IQuoteDao {
    /**
     * @return Quote A random quote from a list of quotes.
     */
    Quote getRandomQuote();
}
