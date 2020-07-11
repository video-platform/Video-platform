package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.dto.ResponseMessage;

public interface MemberService {

    ResponseMessage memberSignUp(Member member);

}
