package com.video.demo.repository;

import com.video.demo.domain.PlaylistVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideo,Long> {
}
