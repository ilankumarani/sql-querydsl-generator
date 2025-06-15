package xio.ilan.sql.query.dsl;

import lombok.Builder;

import javax.annotation.processing.Generated;

/**
 * BBlogDetails is a Querydsl bean type
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.BeanSerializer")
@Builder
public class BBlogDetails {

    public BBlogDetails() {
    }

    public BBlogDetails(java.util.Date blogDate, java.time.Instant blogInstant, java.time.LocalDate blogLocalDate, java.time.LocalDateTime blogLocalDateTime, java.time.LocalTime blogLocalTime, java.time.OffsetDateTime blogOffsetDateTime, java.sql.Date blogSqlDate, java.sql.Time blogSqlTime, java.sql.Timestamp blogSqlTimestamp, java.time.ZonedDateTime blogZonedDateTime, String category, String content, Long id, String title) {
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

    private java.util.Date blogDate;

    private java.time.Instant blogInstant;

    private java.time.LocalDate blogLocalDate;

    private java.time.LocalDateTime blogLocalDateTime;

    private java.time.LocalTime blogLocalTime;

    private java.time.OffsetDateTime blogOffsetDateTime;

    private java.sql.Date blogSqlDate;

    private java.sql.Time blogSqlTime;

    private java.sql.Timestamp blogSqlTimestamp;

    private java.time.ZonedDateTime blogZonedDateTime;

    private String category;

    private String content;

    private Long id;

    private String title;

    public java.util.Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(java.util.Date blogDate) {
        this.blogDate = blogDate;
    }

    public java.time.Instant getBlogInstant() {
        return blogInstant;
    }

    public void setBlogInstant(java.time.Instant blogInstant) {
        this.blogInstant = blogInstant;
    }

    public java.time.LocalDate getBlogLocalDate() {
        return blogLocalDate;
    }

    public void setBlogLocalDate(java.time.LocalDate blogLocalDate) {
        this.blogLocalDate = blogLocalDate;
    }

    public java.time.LocalDateTime getBlogLocalDateTime() {
        return blogLocalDateTime;
    }

    public void setBlogLocalDateTime(java.time.LocalDateTime blogLocalDateTime) {
        this.blogLocalDateTime = blogLocalDateTime;
    }

    public java.time.LocalTime getBlogLocalTime() {
        return blogLocalTime;
    }

    public void setBlogLocalTime(java.time.LocalTime blogLocalTime) {
        this.blogLocalTime = blogLocalTime;
    }

    public java.time.OffsetDateTime getBlogOffsetDateTime() {
        return blogOffsetDateTime;
    }

    public void setBlogOffsetDateTime(java.time.OffsetDateTime blogOffsetDateTime) {
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

    public java.time.ZonedDateTime getBlogZonedDateTime() {
        return blogZonedDateTime;
    }

    public void setBlogZonedDateTime(java.time.ZonedDateTime blogZonedDateTime) {
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

