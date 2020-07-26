package com.video.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class PlaylistVideo {

    @ManyToOne
    @JoinColumn(name = "playlist_no")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "video_no")
    private Video video;
}
