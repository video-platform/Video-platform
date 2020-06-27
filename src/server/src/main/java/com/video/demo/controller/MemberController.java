package com.video.demo.controller;

import com.video.demo.domain.Member;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> memberSignUp(@RequestBody Member member){

        return null;
    }


}
