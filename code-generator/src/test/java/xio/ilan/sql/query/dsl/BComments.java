package xio.ilan.sql.query.dsl;

import lombok.Builder;

import javax.annotation.processing.Generated;

/**
 * BComments is a Querydsl bean type
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.BeanSerializer")
@Builder
public class BComments {

    public BComments() {
    }

    public BComments(Boolean approved, String author, String email, Long id, java.sql.Timestamp postedat, Long postId, String text) {
        this.approved = approved;
        this.author = author;
        this.email = email;
        this.id = id;
        this.postedat = postedat;
        this.postId = postId;
        this.text = text;
    }

    private Boolean approved;

    private String author;

    private String email;

    private Long id;

    private java.sql.Timestamp postedat;

    private Long postId;

    private String text;

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Timestamp getPostedat() {
        return postedat;
    }

    public void setPostedat(java.sql.Timestamp postedat) {
        this.postedat = postedat;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
         return "approved = " + approved + ", author = " + author + ", email = " + email + ", id = " + id + ", postedat = " + postedat + ", postId = " + postId + ", text = " + text;
    }

}

