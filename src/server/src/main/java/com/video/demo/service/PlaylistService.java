package com.video.demo.service;

import com.video.demo.domain.Playlist;
import com.video.demo.domain.dto.ResponseMessage;

public interface PlaylistService {
    ResponseMessage createPlaylist(Playlist playlist);
}
