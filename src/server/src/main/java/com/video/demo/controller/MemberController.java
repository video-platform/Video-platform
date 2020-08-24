package com.video.demo.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Member;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.security.tokens.PostAuthorizationToken;
import com.video.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> memberSignUp(@RequestBody Member member){
        ResponseMessage responseMessage = memberService.memberSignUp(member);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication){
        PostAuthorizationToken token = (PostAuthorizationToken)authentication;
        return token.getMemberContext().getUsername();
    }


}
