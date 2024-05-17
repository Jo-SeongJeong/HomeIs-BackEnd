package com.homeis.loan.model.service;

import java.util.List;

import com.homeis.loan.dto.Loan;

public interface LoanService {
	// 대출상품 메인 추천 조회
	public List<Loan> selectAll(String category);
	// 조회수 증가
	public int updateView(Loan loan, int age);
	// 대출상품 메인 추천 조회 중단 (고객 맞춤 직업 결혼 유무 5개)
    public List<Loan> recommendTagLoan(Loan loan);
    // 대출상품 메인 추천 조회 중단 (고객 나이 맞춤 5개)
    public List<Loan> sameAgeRecommendLoan(int age);
    // 태그 별 목록
    public List<Loan> selectByTag(Loan loan);
}
