package com.homeis.loan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.loan.dto.Loan;
import com.homeis.loan.model.service.LoanService;
import com.homeis.user.dto.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {
	private final LoanService loanService;

	@GetMapping("/date-recommend")
	public ResponseEntity<?> list() {
		List<Loan> loans = loanService.recommendDateLoan();
		return ResponseEntity.status(200).body(loans);
	}

	@GetMapping("/tag-recommend")
	public ResponseEntity<?> selectByTagRecommend(@RequestBody Loan loan) {
		List<Loan> loans = loanService.recommendTagLoan(loan);
		return ResponseEntity.status(200).body(loans);
	}
	
	@GetMapping("/birth-recommend")
	public ResponseEntity<?> selectByBirth(@RequestBody User user) {
		String[] birth = user.getBirth().split("-");
		int year = 2024 - Integer.parseInt(birth[0]);
		System.out.println(year);
		List<Loan> loans = loanService.sameAgeRecommendLoan(year);
		return ResponseEntity.status(200).body(loans);
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> viewLoan() {
		List<Loan> loans = loanService.viewLoan();
		return ResponseEntity.status(200).body(loans);
	}
	
	@GetMapping("/latest")
	public ResponseEntity<?> latestLoan() {
		List<Loan> loans = loanService.latestLoan();
		return ResponseEntity.status(200).body(loans);
	}
	
	@GetMapping("/tag")
	public ResponseEntity<?> selectByTag(@RequestBody Loan loan) {
		List<Loan> loans = loanService.recommendTagLoan(loan);
		return ResponseEntity.status(200).body(loans);
	}
	
}
