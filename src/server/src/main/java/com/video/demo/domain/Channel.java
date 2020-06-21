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
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHANNEL_NO")
    private Long channelNo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(unique = true,length = 60)
    private String channelName;

    @Column(unique = true)
    private Timestamp channelDate;
}
