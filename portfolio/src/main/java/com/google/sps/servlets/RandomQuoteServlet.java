package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.dao.IQuoteDao;
import com.google.sps.dao.QuoteDao;
import com.google.sps.models.Quote;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a random quote from {@link IQuoteDao} in the response as "application/json" content
 */
@WebServlet("/random-quote")
public final class RandomQuoteServlet extends HttpServlet {
    private IQuoteDao quoteDao;

    @Override
    public void init() {
        quoteDao = new QuoteDao();

        quoteDao.addQuote(new Quote(
                "Without requirements or design, programming is the art of adding bugs to an empty text file.",
                "Louis Srygley"));
        quoteDao.addQuote(new Quote(
                "I think Microsoft named .Net so it wouldn't show up in a Unix directory listing.",
                "Oktal"));
        quoteDao.addQuote(new Quote(
                "If builders built buildings the way programmers wrote programs, then the first woodpecker that came along would destroy civilization.",
                "Gerald Weinberg"));
        quoteDao.addQuote(new Quote(
                "There are two ways to write error-free programs; only the third one works.",
                "Alan J. Perlis"));
        quoteDao.addQuote(new Quote(
                "A good programmer is someone who always looks both ways before crossing a one-way street.",
                "Doug Linder"));
        quoteDao.addQuote(new Quote(
                "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.",
                "Martin Golding"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Quote quote = quoteDao.getRandomQuote();
        Gson gson = new Gson();

        String quoteJson = gson.toJson(quote);
        response.setContentType("application/json");
        response.getWriter().println(quoteJson);
    }
}
