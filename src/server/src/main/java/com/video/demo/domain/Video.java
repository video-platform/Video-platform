package com.video.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
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

    private String videoContent;
    @Column(nullable = false)
    private Timestamp videoUploadDate;

    private String videoTag;

    private String videoAgelimit;


    public Video() {
        channel = null;
        videoCategory = null;
    }
    public Video(String videoName){
        this.videoName=videoName;
    }
}
