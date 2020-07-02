package com.video.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@RequiredArgsConstructor
@Table
public class VideoCategory {
    @Id
    @GeneratedValue
    @Column(name = "VIDEO_CATEGORY_NO")
    private int videoCategoryNo;

    @Column
    private String videoCategoryName;
}
