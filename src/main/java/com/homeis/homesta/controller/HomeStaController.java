package com.homeis.homesta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeis.homesta.model.service.HomestaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/homesta")
@RequiredArgsConstructor
public class HomeStaController {
	private final HomestaService homestaService;
}
