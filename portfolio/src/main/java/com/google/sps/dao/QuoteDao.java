package com.google.sps.dao;

import com.google.sps.models.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteDao implements IQuoteDao{
    private final List<Quote> quoteList = new ArrayList<>();

    public QuoteDao() {
        quoteList.add(new Quote(
                "Without requirements or design, programming is the art of adding bugs to an empty text file.",
                "Louis Srygley"));
        quoteList.add(new Quote(
                "I think Microsoft named .Net so it wouldn't show up in a Unix directory listing.",
                "Oktal"));
        quoteList.add(new Quote(
                "If builders built buildings the way programmers wrote programs, then the first woodpecker that came along would destroy civilization.",
                "Gerald Weinberg"));
        quoteList.add(new Quote(
                "There are two ways to write error-free programs; only the third one works.",
                "Alan J. Perlis"));
        quoteList.add(new Quote(
                "A good programmer is someone who always looks both ways before crossing a one-way street.",
                "Doug Linder"));
        quoteList.add(new Quote(
                "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.",
                "Martin Golding"));
    }

    @Override
    public Quote getRandomQuote() {
        return quoteList.get((int) (Math.random() * quoteList.size()));
    }
}
