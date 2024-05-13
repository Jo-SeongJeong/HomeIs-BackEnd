package com.homeis.loan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.loan.dto.Loan;

@Mapper
public interface LoanMapper {

	// 대출상품 메인 추천 조회 상단 (최신 3개)
    public List<Loan> recommendDateLoan();
	// 대출상품 메인 추천 조회 중단 (고객 맞춤 직업 결혼 유무 5개)
    public List<Loan> recommendTagLoan(Loan loan);
    // 대출상품 메인 추천 조회 중단 (고객 나이 맞춤 5개)
    public List<Loan> sameAgeRecommendLoan(int year);
    // 전체 상품 총 조회수 순
    public List<Loan> viewLoan();
    // 전체 상품 최신 순
    public List<Loan> latestLoan();
    // 태그 별 목록
    public List<Loan> selectByTag(Loan loan);
}
