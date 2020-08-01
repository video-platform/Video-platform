package com.video.demo.service;

import com.video.demo.domain.Playlist;
import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.PlaylistRepository;
import com.video.demo.repository.PlaylistVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PlaylistVideoRepository playlistVideoRepository;

    @Override
    public ResponseMessage createPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);

        return new ResponseMessage(playlist,"플레이리스트를 생성하였습니다.");
    }

    @Override
    public ResponseMessage addPlayList(Playlist playlist, List<Video> videoList) {
        for (Video video : videoList){
            PlaylistVideo playlistVideo = new PlaylistVideo();
            playlistVideo.setPlaylist(playlist);
            playlistVideo.setVideo(video);

            playlistVideoRepository.save(playlistVideo);
        }

        return new ResponseMessage(null,"플레이리스트 추가가 완료되었습니다.");
    }


}
