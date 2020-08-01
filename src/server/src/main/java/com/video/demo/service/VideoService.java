package com.video.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.video.demo.domain.Comments;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    ResponseMessage videoUpload(MultipartFile multipartFile, Video video) throws IOException;
    void videoEncoding(String videoId);
    String getViewerIp(HttpServletRequest request);
    boolean videoViewerCheck(String viewerIp,String videoId);
    List<Comments> getVideoComments(String videoId, int page);
    ResponseMessage addComment(Comments comments);
    ResponseMessage editComment(Comments comments);
    ResponseMessage deleteComment(Comments comments);
}
