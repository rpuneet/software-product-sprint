package com.google.sps.dao;

import com.google.sps.models.Comment;
import com.google.sps.models.ValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CommentDao implements ICommentDao{
    List<Comment> commentList;

    public CommentDao() {
        commentList = new ArrayList<>();
    }


    @Override
    public List<Comment> getAllComments() {
        return commentList;
    }

    @Override
    public Comment getCommentById(int id) {
        if (id < 0 || id >= commentList.size()) {
            return null;
        }
        return commentList.get(id);
    }

    @Override
    public ValidationResponse addComment(Comment comment) {
        ValidationResponse validationResponse = Comment.Validator.validate(comment);
        if (!validationResponse.isValid()) {
            return validationResponse;
        }
        comment.setId(commentList.size());
        commentList.add(comment);
        return validationResponse;
    }
}
