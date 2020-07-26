package com.video.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
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

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp commentDate;

    public Comments(Long commentNo, Video video,Member member,String commentContent, Timestamp commentDate) {
        this.commentNo = commentNo;
        this.video = video;
        this.member = member;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }

}
