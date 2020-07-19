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
 * Returns a random quote from {@link IQuoteDao} in the response as "text/plain"
 */
@WebServlet("/random-quote")
public final class RandomQuoteServlet extends HttpServlet {
    private IQuoteDao quoteDao;

    @Override
    public void init() {
        quoteDao = new QuoteDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Quote quote = quoteDao.getRandomQuote();
        Gson gson = new Gson();

        String quoteJson = gson.toJson(quote);
        response.setContentType("text/json");
        response.getWriter().println(quoteJson);
    }
}
