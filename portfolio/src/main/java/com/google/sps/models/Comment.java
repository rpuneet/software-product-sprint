package com.google.sps.models;

import java.util.Date;

public class Comment {
    private String commentText;
    private long createdAt;
    private long id;
    private String createdBy;

    public Comment(String commentText, long createdAt, long id, String createdBy) {
        this.commentText = commentText;
        this.createdAt = createdAt;
        this.id = id;
        this.createdBy = createdBy;
    }

    public Comment(String commentText, String createdBy) {
        this.commentText = commentText;
        this.createdBy = createdBy;
        this.createdAt = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public static final class Validator {
        private static boolean isEmptyOrWhiteSpace(String text) {
            return text == null || text.trim().isEmpty();
        }

        public static ValidationResponse validate(Comment comment) {
            ValidationResponse validationResponse = new ValidationResponse(true);

            if (isEmptyOrWhiteSpace(comment.getCommentText())) {
                validationResponse.setValid(false);
            }

            if (isEmptyOrWhiteSpace(comment.getCreatedBy())) {
                validationResponse.setValid(false);
            }

            return validationResponse;
        }
    }

    public static final class Keys {
        public static final String KIND = "comment";
        public static final String COMMENT_TEXT = "commentText";
        public static final String CREATED_AT = "createdAt";
        public static final String CREATED_BY = "createdBy";
        public static final String ID = "id";

    }
}
