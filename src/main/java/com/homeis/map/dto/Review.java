package com.homeis.map.dto;

import lombok.Data;

@Data
public class Review {

    private int id;
    private String userId;
    private String aptCode;
    private String content;
    private String createTime;
    private String score;
}

