package com.video.demo.service;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;

import java.util.List;

public interface ChannelService {
    ResponseMessage createChannel(Channel channel);
    ResponseMessage deleteChannel(Channel channel);

}
