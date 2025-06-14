package com.ilan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "BLOG_DETAILS", schema = "BLOG_SCHEMA")
public class Blog {

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

    @Column(name = "BLOG_DATE")
    private Date blogDate;

    @Column(name = "BLOG_LOCAL_DATE")
    private LocalDate blogLocalDate;

    @Column(name = "BLOG_LOCAL_TIME")
    private LocalTime blogLocalTime;

    @Column(name = "BLOG_LOCAL_DATE_TIME")
    private LocalDateTime blogLocalDateTime;
}
