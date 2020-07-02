package com.video.demo.controller;

import com.video.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/upload")
    public String videoUploadPage() {
        return "";
    }

    @PostMapping("/upload")
    public String videoUpload() {
        return "";
    }

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
}
