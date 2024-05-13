package com.homeis.loan.dto;

import lombok.Data;

@Data
public class Loan {
	private int id;
    private String name;
    private String description;
    private double interestRate;
    private String link;
    private int youth;
    private int worker;
    private int businessman;
    private int married;
    private int homeless;
    private String realEstate;
    private int views;
    private int viewsAge019;
    private int viewsAge2029;
    private int viewsAge3039;
    private int viewsAge4049;
    private int viewsAge5059;
    private int viewsAge60;
    private String releaseDate;
    private String bank;
}
