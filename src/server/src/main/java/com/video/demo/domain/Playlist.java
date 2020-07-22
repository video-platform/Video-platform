package com.video.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long PlaylistNo;

    @ManyToOne
    @JoinColumn(name = "channel_no")
    private Channel channel;
    @Column
    private String PlaylistName;
    @Column
    private Timestamp PlaylistUpdate;
}
