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
        channelRepository.deleteById(channel.getChannelNo());

        return new ResponseMessage(null,"채널이 삭제되었습니다.");
    }

    @Override
    public ResponseMessage getChannel(Channel channel) {
        Channel getChannel = channelRepository.getOne(channel.getChannelNo());

        return new ResponseMessage(getChannel,"채널정보를 불러왔습니다.");
    }

    @Override
    public ResponseMessage editChannel(Channel channel) {
        Channel originChannel = channelRepository.getOne(channel.getChannelNo());
        originChannel.setChannelName(channel.getChannelName());

        channelRepository.save(originChannel);

        return new ResponseMessage(originChannel,"채널정보를 수정하였습니다.");
    }
}
