package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.dao.CommentDao;
import com.google.sps.dao.ICommentDao;
import com.google.sps.dao.QuoteDao;
import com.google.sps.models.Comment;
import com.google.sps.models.ValidationResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {
    private ICommentDao commentDao;
    private final Gson gson = new Gson();

    @Override
    public void init() {
        commentDao = new CommentDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Comment> commentList = commentDao.getAllComments();

        String commentListJson = gson.toJson(commentList);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(commentListJson);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commentText = getParameter(request, Comment.Keys.COMMENT_TEXT, "");
        String createdBy = getParameter(request, Comment.Keys.CREATED_BY, "");

        Comment comment = new Comment(commentText, createdBy);
        ValidationResponse validationResponse = commentDao.addComment(comment);
        String validationResponseJson = gson.toJson(validationResponse);

        response.setContentType("application/json");
        if (validationResponse.isValid()) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.getWriter().println(validationResponseJson);
    }

    private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
