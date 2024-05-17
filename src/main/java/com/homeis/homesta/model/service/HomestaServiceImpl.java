package com.homeis.homesta.model.service;

import org.springframework.stereotype.Service;

import com.homeis.homesta.model.mapper.HomestaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomestaServiceImpl implements HomestaService {
	private final HomestaMapper homestaMapper;

}
