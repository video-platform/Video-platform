package com.video.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Getter
@EqualsAndHashCode
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long commentNo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "VIDEO_ID")
    private Video video;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private Timestamp commentDate;
}
