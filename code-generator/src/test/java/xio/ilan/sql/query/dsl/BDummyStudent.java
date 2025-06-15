package xio.ilan.sql.query.dsl;

import javax.annotation.processing.Generated;

/**
 * BDummyStudent is a Querydsl bean type
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.BeanSerializer")
public class BDummyStudent {

    public BDummyStudent() {
    }

    public BDummyStudent(java.sql.Timestamp blogDate, java.sql.Timestamp blogInstant, java.sql.Date blogLocalDate, java.sql.Timestamp blogLocalDateTime, java.sql.Time blogLocalTime, java.sql.Timestamp blogOffsetDateTime, java.sql.Date blogSqlDate, java.sql.Time blogSqlTime, java.sql.Timestamp blogSqlTimestamp, java.sql.Timestamp blogZonedDateTime, String category, String content, Long id, String title) {
        this.blogDate = blogDate;
        this.blogInstant = blogInstant;
        this.blogLocalDate = blogLocalDate;
        this.blogLocalDateTime = blogLocalDateTime;
        this.blogLocalTime = blogLocalTime;
        this.blogOffsetDateTime = blogOffsetDateTime;
        this.blogSqlDate = blogSqlDate;
        this.blogSqlTime = blogSqlTime;
        this.blogSqlTimestamp = blogSqlTimestamp;
        this.blogZonedDateTime = blogZonedDateTime;
        this.category = category;
        this.content = content;
        this.id = id;
        this.title = title;
    }

    private java.sql.Timestamp blogDate;

    private java.sql.Timestamp blogInstant;

    private java.sql.Date blogLocalDate;

    private java.sql.Timestamp blogLocalDateTime;

    private java.sql.Time blogLocalTime;

    private java.sql.Timestamp blogOffsetDateTime;

    private java.sql.Date blogSqlDate;

    private java.sql.Time blogSqlTime;

    private java.sql.Timestamp blogSqlTimestamp;

    private java.sql.Timestamp blogZonedDateTime;

    private String category;

    private String content;

    private Long id;

    private String title;

    public java.sql.Timestamp getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(java.sql.Timestamp blogDate) {
        this.blogDate = blogDate;
    }

    public java.sql.Timestamp getBlogInstant() {
        return blogInstant;
    }

    public void setBlogInstant(java.sql.Timestamp blogInstant) {
        this.blogInstant = blogInstant;
    }

    public java.sql.Date getBlogLocalDate() {
        return blogLocalDate;
    }

    public void setBlogLocalDate(java.sql.Date blogLocalDate) {
        this.blogLocalDate = blogLocalDate;
    }

    public java.sql.Timestamp getBlogLocalDateTime() {
        return blogLocalDateTime;
    }

    public void setBlogLocalDateTime(java.sql.Timestamp blogLocalDateTime) {
        this.blogLocalDateTime = blogLocalDateTime;
    }

    public java.sql.Time getBlogLocalTime() {
        return blogLocalTime;
    }

    public void setBlogLocalTime(java.sql.Time blogLocalTime) {
        this.blogLocalTime = blogLocalTime;
    }

    public java.sql.Timestamp getBlogOffsetDateTime() {
        return blogOffsetDateTime;
    }

    public void setBlogOffsetDateTime(java.sql.Timestamp blogOffsetDateTime) {
        this.blogOffsetDateTime = blogOffsetDateTime;
    }

    public java.sql.Date getBlogSqlDate() {
        return blogSqlDate;
    }

    public void setBlogSqlDate(java.sql.Date blogSqlDate) {
        this.blogSqlDate = blogSqlDate;
    }

    public java.sql.Time getBlogSqlTime() {
        return blogSqlTime;
    }

    public void setBlogSqlTime(java.sql.Time blogSqlTime) {
        this.blogSqlTime = blogSqlTime;
    }

    public java.sql.Timestamp getBlogSqlTimestamp() {
        return blogSqlTimestamp;
    }

    public void setBlogSqlTimestamp(java.sql.Timestamp blogSqlTimestamp) {
        this.blogSqlTimestamp = blogSqlTimestamp;
    }

    public java.sql.Timestamp getBlogZonedDateTime() {
        return blogZonedDateTime;
    }

    public void setBlogZonedDateTime(java.sql.Timestamp blogZonedDateTime) {
        this.blogZonedDateTime = blogZonedDateTime;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
         return "blogDate = " + blogDate + ", blogInstant = " + blogInstant + ", blogLocalDate = " + blogLocalDate + ", blogLocalDateTime = " + blogLocalDateTime + ", blogLocalTime = " + blogLocalTime + ", blogOffsetDateTime = " + blogOffsetDateTime + ", blogSqlDate = " + blogSqlDate + ", blogSqlTime = " + blogSqlTime + ", blogSqlTimestamp = " + blogSqlTimestamp + ", blogZonedDateTime = " + blogZonedDateTime + ", category = " + category + ", content = " + content + ", id = " + id + ", title = " + title;
    }

}

