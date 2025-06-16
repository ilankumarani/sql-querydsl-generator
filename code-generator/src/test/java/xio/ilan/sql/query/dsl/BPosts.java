package xio.ilan.sql.query.dsl;

import lombok.Builder;

import javax.annotation.processing.Generated;

/**
 * BPosts is a Querydsl bean type
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.BeanSerializer")
@Builder
public class BPosts {

    public BPosts() {
    }

    public BPosts(String category, String content, java.sql.Timestamp createdat, Long id, Boolean published, String title, java.sql.Timestamp updatedat, Long userId) {
        this.category = category;
        this.content = content;
        this.createdat = createdat;
        this.id = id;
        this.published = published;
        this.title = title;
        this.updatedat = updatedat;
        this.userId = userId;
    }

    private String category;

    private String content;

    private java.sql.Timestamp createdat;

    private Long id;

    private Boolean published;

    private String title;

    private java.sql.Timestamp updatedat;

    private Long userId;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(java.sql.Timestamp createdat) {
        this.createdat = createdat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.sql.Timestamp getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(java.sql.Timestamp updatedat) {
        this.updatedat = updatedat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
         return "category = " + category + ", content = " + content + ", createdat = " + createdat + ", id = " + id + ", published = " + published + ", title = " + title + ", updatedat = " + updatedat + ", userId = " + userId;
    }

}

