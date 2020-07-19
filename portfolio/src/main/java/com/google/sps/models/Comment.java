package com.google.sps.models;

import java.time.LocalDateTime;

public class Comment {
    private String commentText;
    private LocalDateTime lastModified;
    private int id;

    public Comment(String commentText) {
        this.commentText = commentText;
        this.lastModified = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public static class Validator {
        private static boolean isEmptyOrWhiteSpace(String text) {
            return text == null || text.trim().isEmpty();
        }

        public static ValidationResponse validate(Comment comment) {
            ValidationResponse validationResponse = new ValidationResponse(true);

            if (isEmptyOrWhiteSpace(comment.getCommentText())) {
                validationResponse.setValid(false);
            }
            return validationResponse;
        }
    }
}
