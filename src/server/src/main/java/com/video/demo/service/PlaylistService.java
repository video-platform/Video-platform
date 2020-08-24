package com.video.demo.service;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;

import java.util.List;

public interface PlaylistService {
    ResponseMessage createPlaylist(Playlist playlist);
    ResponseMessage editPlayList(Playlist playlist);
    ResponseMessage deletePlaylist(Playlist playlist);
    ResponseMessage getPlayList(Channel channel);
    ResponseMessage addPlayListVideo(Playlist playlist, List<Video> videoList);
    ResponseMessage deletePlaylistVideo(List<PlaylistVideo> playlistVideoList);
    ResponseMessage getPlaylistVideo(Playlist playlist);

}
