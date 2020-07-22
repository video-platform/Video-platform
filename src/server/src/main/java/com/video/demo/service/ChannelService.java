package com.video.demo.service;

import com.video.demo.domain.Channel;
import com.video.demo.domain.dto.ResponseMessage;

public interface ChannelService {
    ResponseMessage createChannel(Channel channel);
    ResponseMessage deleteChannel(Channel channel);
}
