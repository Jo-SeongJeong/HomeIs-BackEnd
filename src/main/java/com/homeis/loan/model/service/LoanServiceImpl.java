package com.homeis.loan.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.homeis.loan.dto.Loan;
import com.homeis.loan.model.mapper.LoanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

	private final LoanMapper loanMapper;
	
	@Override
	public List<Loan> selectAll(String category) {
		Map<String, Object> param = new HashMap<>();
		param.put("category", category);
		
		return loanMapper.selectAll(param);
	}
	
	@Override
	public int updateView(Loan loan, int age) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", loan.getId());
		param.put("age", age);
		
		return loanMapper.updateView(param);
	}

	@Override
	public List<Loan> recommendTagLoan(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.recommendTagLoan(loan);
	}

	@Override
	public List<Loan> sameAgeRecommendLoan(int age) {
		// TODO Auto-generated method stub
		return loanMapper.sameAgeRecommendLoan(age);
	}

	@Override
	public List<Loan> selectByTag(Loan loan) {
		// TODO Auto-generated method stub
		return loanMapper.selectByTag(loan);
	}

}
