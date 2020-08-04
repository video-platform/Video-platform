package com.video.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode
@ToString
@Table
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "channel_no")
    private Long channelNo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(unique = true,length = 60)
    private String channelName;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp channelDate;
}
