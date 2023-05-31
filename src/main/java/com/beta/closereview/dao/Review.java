package com.beta.closereview.dao;

public class Review {
    private Integer reviewer;

    private Integer submission;

    private Integer initialScore;

    private Integer confidence;

    private Integer finalScore;

    private String initialComment;

    private String rebuttal;

    private String finalComment;

    public Integer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Integer reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getSubmission() {
        return submission;
    }

    public void setSubmission(Integer submission) {
        this.submission = submission;
    }

    public Integer getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(Integer initialScore) {
        this.initialScore = initialScore;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public String getInitialComment() {
        return initialComment;
    }

    public void setInitialComment(String initialComment) {
        this.initialComment = initialComment == null ? null : initialComment.trim();
    }

    public String getRebuttal() {
        return rebuttal;
    }

    public void setRebuttal(String rebuttal) {
        this.rebuttal = rebuttal == null ? null : rebuttal.trim();
    }

    public String getFinalComment() {
        return finalComment;
    }

    public void setFinalComment(String finalComment) {
        this.finalComment = finalComment == null ? null : finalComment.trim();
    }
}