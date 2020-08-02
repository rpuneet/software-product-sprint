package com.google.sps.dao;

import com.google.appengine.api.datastore.*;
import com.google.sps.models.Comment;
import com.google.sps.models.ValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class CommentDao implements ICommentDao{
    DatastoreService datastoreService;

    public CommentDao() {
        datastoreService = DatastoreServiceFactory.getDatastoreService();
    }

    @Override
    public List<Comment> getAllComments() {
        Query query = new Query(Comment.Keys.KIND)
                .addSort(Comment.Keys.CREATED_AT, Query.SortDirection.DESCENDING);

        PreparedQuery results = datastoreService.prepare(query);

        List<Comment> commentList = new ArrayList<>();

        for (Entity entity : results.asIterable()) {
            long id = entity.getKey().getId();
            String commentText = (String) entity.getProperty(Comment.Keys.COMMENT_TEXT);
            long createdAt = (long) entity.getProperty(Comment.Keys.CREATED_AT);
            String createdBy = (String) entity.getProperty(Comment.Keys.CREATED_BY);
            Comment comment = new Comment(commentText, createdAt, id, createdBy);
            commentList.add(comment);
        }

        return commentList;
    }

    @Override
    public Comment getCommentById(long id) {
        Key commentEntityKey = KeyFactory.createKey(Comment.Keys.KIND, id);
        try {
            Entity commentEntity = datastoreService.get(commentEntityKey);

            String commentText = (String) commentEntity.getProperty(Comment.Keys.COMMENT_TEXT);
            long createdAt = (long) commentEntity.getProperty(Comment.Keys.CREATED_AT);
            String createdBy = (String) commentEntity.getProperty(Comment.Keys.CREATED_BY);

            return new Comment(commentText, createdAt, id, createdBy);
        } catch (EntityNotFoundException entityNotFoundException) {
            return null;
        }
    }

    @Override
    public ValidationResponse addComment(Comment comment) {
        ValidationResponse validationResponse = Comment.Validator.validate(comment);
        if (!validationResponse.isValid()) {
            return validationResponse;
        }

        Entity commentEntity = new Entity(Comment.Keys.KIND);

        commentEntity.setProperty(Comment.Keys.COMMENT_TEXT, comment.getCommentText());
        commentEntity.setProperty(Comment.Keys.CREATED_AT, comment.getCreatedAt());
        commentEntity.setProperty(Comment.Keys.CREATED_BY, comment.getCreatedBy());

        datastoreService.put(commentEntity);

        return validationResponse;
    }
}
