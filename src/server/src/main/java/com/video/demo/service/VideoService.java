package com.video.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface VideoService {
    String videoUpload(MultipartFile multipartFile) throws IOException;
    void videoEncoding(String videoId);
    String getViewerIp(HttpServletRequest request);
    boolean videoViewerCheck(String viewerIp,String videoId);
}
