package com.video.demo.service;

import com.video.demo.domain.Channel;
import com.video.demo.domain.Playlist;
import com.video.demo.domain.PlaylistVideo;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.ChannelRepository;
import com.video.demo.repository.PlaylistVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public ResponseMessage createChannel(Channel channel) {
        channelRepository.save(channel);

        return new ResponseMessage(channelRepository.getOne(channel.getChannelNo()),"채널을 성공적으로 개설했습니다.");
    }

    @Override
    public ResponseMessage deleteChannel(Channel channel) {
        channelRepository.delete(channel);

        return new ResponseMessage(null,"채널이 삭제되었습니다.");
    }


}
