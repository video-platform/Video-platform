package com.video.demo.repository;

import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideo,Long> {
    List<PlaylistVideo> findByPlaylist_PlaylistNoOrderByPlaylistVideoNo(Long playlistNo);
}
