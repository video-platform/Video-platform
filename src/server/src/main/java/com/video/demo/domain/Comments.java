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
    private String videoId;
    private Long commentMemberNo;
    private String commentContent;
    private Timestamp commentDate;
}
