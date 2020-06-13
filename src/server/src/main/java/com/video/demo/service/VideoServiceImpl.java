package com.video.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class VideoServiceImpl implements VideoService{

    static final String VIDEO_PATH = "/Users/parksungwoo/Desktop/demoVideo";

    @Override
    public String videoUpload(MultipartFile multipartFile) throws IOException {
        byte[] data = multipartFile.getBytes();
        String uploadVideoName = createUploadFileName();
        FileOutputStream fileOutputStream = new FileOutputStream(VIDEO_PATH+"/"+uploadVideoName);
        fileOutputStream.write(data);
        fileOutputStream.close();

        return uploadVideoName;
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
    public void videoIncoding(String videoId){
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

}
