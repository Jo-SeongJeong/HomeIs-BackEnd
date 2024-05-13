package com.homeis.loan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeis.loan.dto.Loan;
import com.homeis.loan.model.mapper.LoanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

	private final LoanMapper loanMapper;

	@Override
	public List<Loan> recommendDateLoan() {
		// TODO Auto-generated method stub
		return loanMapper.recommendDateLoan();
	}

	@Override
	public List<Loan> recommendTagLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.recommendTagLoan(loan);
	}

	@Override
	public List<Loan> sameAgeRecommendLoan(int year) {
		// TODO Auto-generated method stub
		return loanMapper.sameAgeRecommendLoan(year);
	}

	@Override
	public List<Loan> viewLoan() {
		// TODO Auto-generated method stub
		return loanMapper.viewLoan();
	}

	@Override
	public List<Loan> latestLoan() {
		// TODO Auto-generated method stub
		return loanMapper.latestLoan();
	}

	@Override
	public List<Loan> selectByTag(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.selectByTag(loan);
	}

}
