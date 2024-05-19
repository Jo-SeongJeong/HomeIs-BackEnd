package com.homeis.homesta.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.homeis.homesta.dto.Homesta;
import com.homeis.homesta.dto.HomestaImage;
import com.homeis.homesta.dto.HomestaLike;

@Mapper
public interface HomestaMapper {
	public List<Homesta> selectAll(Map<String, Object> param);
	/**
	 * 조회한 목록에 대한 전체 
	 * @param param
	 * @return
	 */
	int totalRow(Map<String, Object> param);
	public List<HomestaImage> selectImage(int homestaId);
	public int increaseView(int id);
	public Homesta findById(int id);
	public int insertHomesta(Homesta homesta);
	public int selectLast();
	public int insertImage(HomestaImage homestaImage);
	public int updateHomesta(Homesta homesta);
	public int deleteImage(int id);
	public int deleteHomesta(Map<String, Object> param);
	public int insertLike(HomestaLike like);
	public int increaseLike(int id);
	public int deleteLike(HomestaLike like);
	public int decreaseLike(int id);
	
}
