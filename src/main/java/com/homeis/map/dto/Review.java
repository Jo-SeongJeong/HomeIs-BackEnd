package com.homeis.map.dto;

import lombok.Data;

@Data
public class Review {

    private int id;
    private String user_id;
    private String aptCode;
    private String apartment_name;
    private String content;
    private String create_time;
    private String score;
}

