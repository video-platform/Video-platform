package com.video.demo.service;

import com.video.demo.domain.Playlist;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public ResponseMessage createPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);

        return new ResponseMessage(playlist,"플레이리스트를 생성하였습니다.");
    }


}
