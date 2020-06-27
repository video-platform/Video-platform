package com.video.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {

    @JsonProperty("memberEmail")
    private String id = null;

    @JsonProperty("MemberPw")
    private String password = null;

}
