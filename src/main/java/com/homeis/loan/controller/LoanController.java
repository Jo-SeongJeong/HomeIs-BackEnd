package com.homeis.loan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.loan.dto.Loan;
import com.homeis.loan.model.service.LoanService;
import com.homeis.user.dto.User;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {
	private final LoanService loanService;
	private final JWTUtil jwtUtil;

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam(value ="category", defaultValue = "release_date") String category) {
		List<Loan> loanList = loanService.selectAll(category);
		return ResponseEntity.ok(loanList);
	}
	
	@PatchMapping("/plusView")
	public ResponseEntity<?> increaseView(@RequestBody Loan loan , @RequestHeader("Authorization") String tokenHeader) {
		
		
		String[] birth = jwtUtil.getbirthFromToken(tokenHeader.substring(7)).split("-");
		
		int age = 2024 - Integer.parseInt(birth[0]);
		System.out.println(age);
		
		loanService.updateView(loan, age);
		
		return ResponseEntity.status(200).build();
		
	}

	@GetMapping("/tag-recommend")
	public ResponseEntity<?> selectByTagRecommend(@RequestBody Loan loan) {
		List<Loan> loanList = loanService.recommendTagLoan(loan);
		return ResponseEntity.status(200).body(loanList);
	}
	
	@GetMapping("/birth-recommend")
	public ResponseEntity<?> selectByBirth(@RequestBody User user) {
		String[] birth = user.getBirth().split("-");
		int year = 2024 - Integer.parseInt(birth[0]);
		System.out.println(year);
		List<Loan> loanList = loanService.sameAgeRecommendLoan(year);
		return ResponseEntity.status(200).body(loanList);
	}
	
	@GetMapping("/tag")
	public ResponseEntity<?> selectByTag(@RequestBody Loan loan) {
		List<Loan> loanList = loanService.recommendTagLoan(loan);
		return ResponseEntity.status(200).body(loanList);
	}
	
}
