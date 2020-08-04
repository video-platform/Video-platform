package com.video.demo.service;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.PlaylistRepository;
import com.video.demo.repository.PlaylistVideoRepository;
import com.video.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PlaylistVideoRepository playlistVideoRepository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public ResponseMessage createPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);

        return new ResponseMessage(playlist,"플레이리스트를 생성하였습니다.");
    }

    @Override
    public ResponseMessage editPlayList(Playlist playlist) {
        Playlist originPlaylist = playlistRepository.getOne(playlist.getPlaylistNo());
        playlist.setChannel(originPlaylist.getChannel());
        playlistRepository.save(playlist);

        return new ResponseMessage(playlist,"플레이리스트를 수정하였습니다.");
    }

    @Override
    public ResponseMessage getPlayList(Channel channel) {

        return new ResponseMessage(playlistRepository.findByChannel_ChannelNoOrderByPlaylistNoDesc(channel.getChannelNo())
                ,"플레이리스트를 불러왔습니다.");
    }

    @Override
    public ResponseMessage addPlayList(Playlist playlist, List<Video> videoList) {
        for (Video video : videoList){
            PlaylistVideo playlistVideo = new PlaylistVideo();
            playlistVideo.setPlaylist(playlistRepository.getOne(playlist.getPlaylistNo()));
            playlistVideo.setVideo(videoRepository.getOne(video.getVideoId()));

            playlistVideoRepository.save(playlistVideo);
        }

        return new ResponseMessage(null,"플레이리스트 추가가 완료되었습니다.");
    }

    @Override
    public ResponseMessage deletePlaylist(List<PlaylistVideo> playlistVideoList) {
        for (PlaylistVideo playlistVideo : playlistVideoList){
            playlistVideoRepository.delete(playlistVideo);
        }

        return new ResponseMessage(null, "플레이리스트 수정이 완료되었습니다.");
    }

    @Override
    public ResponseMessage getPlaylistVideo(Playlist playlist) {
        return new ResponseMessage(playlistVideoRepository.findByPlaylist_PlaylistNoOrderByPlaylistVideoNo(playlist.getPlaylistNo())
                ,"해당 리스트의 비디오를 불러왔습니다.");
    }
}
