package com.video.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode
@ToString
@Table
public class Video {
    @Id
    private String videoId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CHANNEL_NO")
    private Channel channel;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "VIDEO_CATEGORY_NO")
    private VideoCategory videoCategory;

    @Column(nullable = false)
    private String videoName;

    @Column(nullable = false,length = 600)
    private String videoContent;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp videoUploadDate;

    @Column
    private String videoTag;

    @Column
    private String videoAgelimit;

    @Column
    private int videoViewCount;

    public Video() {
    }
}
