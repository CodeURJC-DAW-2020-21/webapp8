package com.practicaweb.practicadaw.api.comment;

public class CommentDTO {

    private long idEntry;
    private String descriptionComment;

    public long getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(long idEntry) {
        this.idEntry = idEntry;
    }

    public String getDescriptionComment() {
        return descriptionComment;
    }

    public void setDescriptionComment(String descriptionComment) {
        this.descriptionComment = descriptionComment;
    }
}
