package com.video.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class PlaylistVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playlistVideoNo;

    @ManyToOne
    @JoinColumn(name = "playlist_no")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "video_no")
    private Video video;
}
