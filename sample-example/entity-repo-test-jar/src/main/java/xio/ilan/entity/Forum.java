package xio.ilan.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "${xio.ilan.entity.name:FORUM_DETAILS}", schema = "${xio.ilan.entity.schema:FORUM_SCHEMA}")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FORUM_NAME")
    private String forumName;

    @Column(name = "FORUM_LIKE")
    private String forumLike;

}
