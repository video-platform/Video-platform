package com.video.demo.domain.dto;

import lombok.Data;

@Data
public class PageRequest {
    private int page;
    private int pageSize;
}
