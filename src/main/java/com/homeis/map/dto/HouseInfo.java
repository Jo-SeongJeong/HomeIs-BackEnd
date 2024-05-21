package com.homeis.map.dto;

import lombok.Data;

@Data
public class HouseInfo {
	private String aptCode;
    private String dongCode;
    private int buildYear;
    private String dong;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
}
