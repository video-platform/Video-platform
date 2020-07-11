package com.video.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {

    @JsonProperty("memberEmail")
    private String memberEmail;

    @JsonProperty("memberPw")
    private String memberPw;

}
