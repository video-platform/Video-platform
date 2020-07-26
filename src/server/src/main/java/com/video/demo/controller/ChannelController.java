package com.video.demo.controller;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.service.ChannelService;
import com.video.demo.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("createChannel")
    public ResponseEntity<ResponseMessage> createChannel(@RequestBody Channel channel){

        return new ResponseEntity<>(channelService.createChannel(channel), HttpStatus.OK);
    }

    @PostMapping("createPlayList")
    public ResponseEntity<ResponseMessage> createPlayList(@RequestBody Playlist playlist){

        return new ResponseEntity<>(playlistService.createPlaylist(playlist),HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> addPlayList(){

        return null;
    }
}
