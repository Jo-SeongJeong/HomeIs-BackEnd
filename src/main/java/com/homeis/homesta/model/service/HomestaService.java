package com.homeis.homesta.model.service;

import com.homeis.homesta.dto.Homesta;
import com.homeis.homesta.dto.HomestaLike;
import com.homeis.homesta.dto.HomestaPaginationResponse;

public interface HomestaService {
	public HomestaPaginationResponse selectAll(int size, int page, String category, String sort);
	public Homesta findById(int id, String userId);
	public int insertHomesta(Homesta homesta);
	public int updateHomesta(Homesta homesta);
	public int deleteHomesta(int id, String userId);
	public int increaseLike(HomestaLike like);
	public int decreaseLike(int id, String userId);
}
