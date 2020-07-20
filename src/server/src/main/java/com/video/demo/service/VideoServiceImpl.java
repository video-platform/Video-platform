package com.video.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Channel;
import com.video.demo.domain.Comments;
import com.video.demo.domain.Video;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.CommentsRepository;
import com.video.demo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class VideoServiceImpl implements VideoService{

    static final String VIDEO_PATH = "/Users/parksungwoo/Desktop/demoVideo";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public ResponseMessage videoUpload(MultipartFile multipartFile, Video video) throws IOException {
        byte[] data = multipartFile.getBytes();
        String uploadVideoName = createUploadFileName();
        try {
            Video saveVideo = Video.builder().videoId(uploadVideoName).channel(video.getChannel()).videoCategory(video.getVideoCategory())
                    .videoName(multipartFile.getOriginalFilename()).videoContent(video.getVideoContent())
                    .videoTag(video.getVideoTag()).videoAgelimit(video.getVideoAgelimit()).build();
            videoRepository.save(saveVideo);
        }catch (EntityExistsException e){

        }
        FileOutputStream fileOutputStream = new FileOutputStream(VIDEO_PATH+"/"+uploadVideoName);
        fileOutputStream.write(data);
        fileOutputStream.close();

        Video saveVideo = videoRepository.getOne(uploadVideoName);

        return new ResponseMessage(saveVideo,"파일 업로드가 성공적으로 완료 되었습니다.");
    }

    //파일 ID 생성
    public String createUploadFileName(){
        String result = "";
        for (int i = 0 ; i < 11 ; i++){
            Random charset = new Random();
            int index = charset.nextInt(3);
            int charNum=0;
            if (index==0){
                charNum = charset.nextInt(26)+65;
            }else if(index==1){
                charNum = charset.nextInt(26)+97;
            }else {
                charNum = 95;
            }
            result= result+(char)charNum;
        }

        return result;
    }


    @Override
    public void videoEncoding(String videoId){
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        List<String> termList = new ArrayList<>();
        termList.add("ffmpeg -i ");
        termList.add(VIDEO_PATH+"/"+videoId+" ");
        termList.add("-profile:v baseline -level 3.0 -s 640x360 -start_number 0 -hls_time\n" +
                " 10 -hls_list_size 0 -f hls ");
        termList.add(videoId+".m3u8");

        String[] array = termList.toArray(new String[termList.size()]);

        try {
            process = runtime.exec(array);

            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            process.destroy();
        }
        File originFile = new File(VIDEO_PATH+"/"+videoId);

        originFile.delete();
    }

    @Override
    public String getViewerIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public boolean videoViewerCheck(String viewerIp,String videoId) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        if (!redisTemplate.hasKey(viewerIp)){
            valueOperations.increment(videoId);
            valueOperations.set(viewerIp,1,10800);
            int videoCount = (int) valueOperations.get(videoId);
            if(videoCount==500){
                //Database에 조회수 저장.
                return true;
            }
        }
        return false;
    }

    static final int pageSize = 10;

    @Override
    public List<Comments> getVideoComments(String videoId, int page){
        PageRequest pageRequest = PageRequest.of(page,pageSize);

        List<Comments> commentsList = commentsRepository.findByVideo_VideoIdOrderByCommentNo(videoId,pageRequest);

        return commentsList;
    }
}
