package com.homeis.user.dto;

import lombok.Data;

@Data
public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private String birth;
	private int point;
	private String job;
	private int married;
	private String sidoName;
	private String gugunName;
	private String dongName;
}
