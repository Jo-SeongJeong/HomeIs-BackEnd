package com.homeis.map.dto;

import lombok.Data;

@Data
public class ApartDealInfo {
    private String aptCode;
    private int buildYear;
    private String roadName;
    private String roadNameCode;
    private String dong;
    private String sigunguCode;
    private String eubmyundongCode;
    private String dongCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
    private int view;

    //거래기록
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String cancelDealType;
}
