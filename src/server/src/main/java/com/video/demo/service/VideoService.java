package com.video.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    String videoUpload(MultipartFile multipartFile) throws IOException;
    void videoEncoding(String videoId);
    String getViewerIp(HttpServletRequest request);
    boolean videoViewerCheck(String viewerIp,String videoId);
    List<String> getVideoComments(String videoId, int page) throws JsonProcessingException;
}
