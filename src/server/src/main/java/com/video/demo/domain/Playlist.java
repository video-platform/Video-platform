package com.video.demo.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long playlistNo;

    @ManyToOne
    @JoinColumn(name = "channel_no")
    private Channel channel;

    @Column
    private String playlistName;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp playlistUpdate;
}
