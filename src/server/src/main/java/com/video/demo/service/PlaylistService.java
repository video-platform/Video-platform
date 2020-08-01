package com.video.demo.service;

import com.video.demo.domain.Playlist;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;

import java.util.List;

public interface PlaylistService {
    ResponseMessage createPlaylist(Playlist playlist);
    ResponseMessage addPlayList(Playlist playlist, List<Video> videoList);
}
