package yov.ilan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHANNEL_DUPLICATE", schema = "BBC_SCHEMA")
public class ChannelDuplicate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CHANNEL_NAME")
    private String channelName;

    @Column(name = "CHANNEL_LIKE")
    private String channelLike;

}
