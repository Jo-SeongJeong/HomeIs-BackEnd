package com.homeis.homesta.dto;

import java.util.List;

import lombok.Data;

@Data
public class Homesta {
	private int id;
	private String title;
	private String content;
	private String creaeteTime;
	private int view;
	private int type;
	private int totalLike;
	
	private List<HomestaImage> image;
}
