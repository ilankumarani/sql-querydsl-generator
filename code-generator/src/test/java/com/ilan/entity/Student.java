package com.ilan.entity;

import jakarta.persistence.*;

import java.time.*;
import java.util.Date;

@Entity
@Table(name = "${com.ilan.entity.student.table.name:STUDENT_DETAILS}", schema = "${com.ilan.entity.student.schema.name:STUDENT_DETAILS_SCHEMA}")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;
    // Legacy Types
    @Column(name = "BLOG_DATE")
    private Date blogDate;

    @Column(name = "BLOG_SQL_DATE")
    private java.sql.Date blogSqlDate;

    @Column(name = "BLOG_SQL_TIME")
    private java.sql.Time blogSqlTime;

    @Column(name = "BLOG_SQL_TIMESTAMP")
    private java.sql.Timestamp blogSqlTimestamp;

    // Java 8+ Date and Time API
    @Column(name = "BLOG_LOCAL_DATE")
    private LocalDate blogLocalDate;

    @Column(name = "BLOG_LOCAL_TIME")
    private LocalTime blogLocalTime;

    @Column(name = "BLOG_LOCAL_DATE_TIME")
    private LocalDateTime blogLocalDateTime;

    @Column(name = "BLOG_OFFSET_DATE_TIME")
    private OffsetDateTime blogOffsetDateTime;

    @Column(name = "BLOG_ZONED_DATE_TIME")
    private ZonedDateTime blogZonedDateTime;

    @Column(name = "BLOG_INSTANT")
    private Instant blogInstant;
}
