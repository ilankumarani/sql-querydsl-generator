package yov.ilan.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "${yov.ilan.name:CHANNEL_DETAILS}", schema = "${yov.ilan.schema:CHANNEL_SCHEMA}")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CHANNEL_NAME")
    private String channelName;

    @Column(name = "CHANNEL_LIKE")
    private String channelLike;

}
