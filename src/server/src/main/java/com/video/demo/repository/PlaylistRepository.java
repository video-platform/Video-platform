package com.video.demo.repository;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    List<Playlist> findByChannel_ChannelNoOrderByPlaylistNoDesc(Long channelNo);
}
