package com.google.sps.dao;

import com.google.sps.models.Comment;
import com.google.sps.models.ValidationResponse;

import java.util.List;

public interface ICommentDao {
    List<Comment> getAllComments();

    Comment getCommentById(long id);

    ValidationResponse addComment(Comment comment);
}
