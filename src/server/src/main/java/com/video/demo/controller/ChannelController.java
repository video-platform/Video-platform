package com.video.demo.controller;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.service.ChannelService;
import com.video.demo.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/channel")
    public ResponseEntity<ResponseMessage> createChannel(@RequestBody Channel channel){

        return new ResponseEntity<>(channelService.createChannel(channel), HttpStatus.OK);
    }
    @DeleteMapping("/channel")
    public ResponseEntity<ResponseMessage> deleteChannel(@RequestBody Channel channel){

        return new ResponseEntity<>(channelService.deleteChannel(channel), HttpStatus.OK);
    }
    @GetMapping("/channel")
    public ResponseEntity<ResponseMessage> getChannel(@RequestBody Channel channel){

        return new ResponseEntity<>(channelService.getChannel(channel),HttpStatus.OK);
    }
    @PutMapping("/channel")
    public  ResponseEntity<ResponseMessage> editChannel(@RequestBody Channel channel){

        return new ResponseEntity<>(channelService.editChannel(channel),HttpStatus.OK);
    }

    @PostMapping("/createPlayList")
    public ResponseEntity<ResponseMessage> createPlayList(@RequestBody Playlist playlist){

        return new ResponseEntity<>(playlistService.createPlaylist(playlist),HttpStatus.OK);
    }

    @PostMapping("/editPlayList")
    public ResponseEntity<ResponseMessage> editPlaylist(@RequestBody Playlist playlist){

        return new ResponseEntity<>(playlistService.editPlayList(playlist), HttpStatus.OK);
    }

    @GetMapping("/getPlayList")
    public ResponseEntity<ResponseMessage> getPlaylist(@RequestBody Channel channel){

        return new ResponseEntity<>(playlistService.getPlayList(channel), HttpStatus.OK);
    }

    @DeleteMapping("/deletePlayList")
    public ResponseEntity<ResponseMessage> deletePlayList(@RequestBody Playlist playlist){

        return new ResponseEntity<>(playlistService.deletePlaylist(playlist),HttpStatus.OK);
    }

    @GetMapping("/getPlayListVideo")
    public ResponseEntity<ResponseMessage> getPlaylistVideo(@RequestBody Playlist playlist){

        return new ResponseEntity<>(playlistService.getPlaylistVideo(playlist),HttpStatus.OK);
    }

    @PostMapping("/addPlayListVideo")
    public ResponseEntity<ResponseMessage> addPlayList(@RequestBody Playlist playlist, @RequestBody List<Video> videoList){

        return new ResponseEntity<>(playlistService.addPlayListVideo(playlist,videoList),HttpStatus.OK);
    }

    //API 문서 업로드 필요
    @DeleteMapping("/deletePlayListVideo")
    public ResponseEntity<ResponseMessage> deletePlayListVideo(@RequestBody List<PlaylistVideo> playlistVideoList){

        return new ResponseEntity<>(playlistService.deletePlaylistVideo(playlistVideoList),HttpStatus.OK);
    }
}
