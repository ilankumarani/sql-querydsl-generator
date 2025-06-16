package com.ilan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "post")
@EqualsAndHashCode(exclude = "post")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String email;

    @Column(length = 2000)
    private String text;

    private boolean approved;
    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
