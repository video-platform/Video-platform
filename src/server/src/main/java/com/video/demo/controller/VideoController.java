package com.video.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Comments;
import com.video.demo.repository.CommentsRepository;
import com.video.demo.repository.VideoRepository;
import com.video.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/upload")
    public String videoUploadPage() {
        return "";
    }

    @PostMapping("/upload")
    public String videoUpload() {
        return "";
    }

    /*
        Mobile 에선 플레이어를 사용해 재생 하지만
        Web에서 사용하는 경우 사용함.
     */
    @GetMapping
    public void videoDownload(String videoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("/Users/parksungwoo/Desktop/demoVideo"+ videoId);

        RandomAccessFile randomFile = new RandomAccessFile(file, "r");

        long rangeStart = 0;
        long rangeEnd = 0;
        boolean isPart=false;

        try{
            long movieSize = randomFile.length();

            String range = request.getHeader("range");

            if(range!=null) {

                if(range.endsWith("-")){
                    range = range+(movieSize - 1);
                }
                int idxm = range.trim().indexOf("-");
                rangeStart = Long.parseLong(range.substring(6,idxm));
                rangeEnd = Long.parseLong(range.substring(idxm+1));
                if(rangeStart > 0){
                    isPart = true;
                }
            }else{
                rangeStart = 0;
                rangeEnd = movieSize - 1;
            }

            long partSize = rangeEnd - rangeStart + 1;

            //전송 시작
            response.reset();

            response.setStatus(isPart ? 206 : 200);

            response.setContentType("video/mp4");


            response.setHeader("Content-Range", "bytes "+rangeStart+"-"+rangeEnd+"/"+movieSize);
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", ""+partSize);

            OutputStream out = response.getOutputStream();

            randomFile.seek(rangeStart);


            int bufferSize = 8*1024;
            byte[] bufArr = new byte[bufferSize];
            while (partSize>0){
                int block = partSize > bufferSize ? bufferSize : (int)partSize;
                int len = randomFile.read(bufArr, 0, block);
                out.write(bufArr, 0, len);
                partSize -= block;
            }

        }catch(IOException e){

        }finally{
            randomFile.close();
        }
    }

    @GetMapping("view")
    public ResponseEntity videoViewPage(@RequestParam String videoId, ObjectMapper objectMapper, Comments comments){

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<String , String> map = objectMapper.convertValue(comments, new TypeReference<Map<String, String>>() {});
        multiValueMap.setAll(map);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("video",videoRepository.findById(videoId));
        modelAndView.addObject("commentList",commentsRepository.findAll());
        modelAndView.addObject("commentCount",commentsRepository.count());



        return null;
    }
}
