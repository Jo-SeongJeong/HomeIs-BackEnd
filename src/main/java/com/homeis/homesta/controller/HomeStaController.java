package com.homeis.homesta.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.homeis.homesta.dto.Homesta;
import com.homeis.homesta.dto.HomestaImage;
import com.homeis.homesta.dto.HomestaLike;
import com.homeis.homesta.dto.HomestaPaginationResponse;
import com.homeis.homesta.model.service.HomestaService;
import com.homeis.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/homesta")
@RequiredArgsConstructor
public class HomeStaController {
	private final HomestaService homestaService;
	private final String uploadPath = "C:/ssafy/save/";
	private final JWTUtil jwtUtil;

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "category", defaultValue = "id") String category,
			@RequestParam(value = "sort", required = false) String sort) {
		HomestaPaginationResponse response = homestaService.selectAll(size, page, category, sort);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<?> detail(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String tokenHeader) {
		String tokenId = null;
		
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));
		}
		
		Homesta homesta = homestaService.findById(id, tokenId);

		if(homesta == null) return ResponseEntity.status(404).body("요청하신 글을 찾을 수 없습니다.");

		return ResponseEntity.ok(homesta);
	}

	@PostMapping("/")
	public ResponseEntity<?> regist(@RequestHeader("Authorization") String tokenHeader, 
			@RequestParam("upfile") MultipartFile[] files, 
			@RequestParam("userId") String userId, 
			@RequestParam("title") String title,
			@RequestParam("content") String content) throws IllegalStateException, java.io.IOException {
		String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

		if(!userId.equals(tokenId))return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
		
		Homesta homesta = new Homesta();
		homesta.setUserId(userId);
		homesta.setTitle(title);
		homesta.setContent(content);
		
		// 파일 배열로 받아옴
		// 빈 배열이면 없는거임
		if (!files[0].isEmpty()) {
			// 경로 생성 날짜 폴더
			String saveFolder = uploadPath;
			File folder = new File(saveFolder);

			// 경로가 없다면 폴더 생성
			if (!folder.exists())
				folder.mkdirs();

			// 반복문으로로 파일 등록
			List<HomestaImage> fileInfos = new ArrayList<HomestaImage>();
			for (MultipartFile mfile : files) {
				HomestaImage homestaImage = new HomestaImage ();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID() + originalFileName;
					homestaImage.setImagePath(saveFolder);
					homestaImage.setSaveName(saveFileName);
					homestaImage.setImageName(originalFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(homestaImage);
			}

			homesta.setImage(fileInfos);
		}

		int result = homestaService.insertHomesta(homesta);

		if(result == 0) return ResponseEntity.status(404).body("실패");

		return ResponseEntity.ok(result);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestHeader("Authorization") String tokenHeader, @RequestBody Homesta homesta) {		
		String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

		if(!homesta.getUserId().equals(tokenId))return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");

		int result = homestaService.updateHomesta(homesta);

		if(result == 0) return ResponseEntity.status(404).body("실패");

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestHeader("Authorization") String tokenHeader) {
		String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

		int result = homestaService.deleteHomesta(id, tokenId);

		if(result == 0) return ResponseEntity.status(404).body("실패");

		return ResponseEntity.ok(result);
	}

	@PostMapping("/like")
	public ResponseEntity<?> insertLike(@RequestBody HomestaLike like, @RequestHeader("Authorization") String tokenHeader) {
		String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

		if(!like.getUserId().equals(tokenId)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");

		int result = homestaService.increaseLike(like);

		if(result == 0) return ResponseEntity.status(404).body("실패");

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/like/{id}")
	public ResponseEntity<?> deleteLike(@PathVariable("id") int id, @RequestHeader("Authorization") String tokenHeader) {
		String tokenId = jwtUtil.getIdFromToken(tokenHeader.substring(7));

		int result = homestaService.decreaseLike(id, tokenId);

		if(result == 0) return ResponseEntity.status(404).body("실패");

		return ResponseEntity.ok(result);
	}
}
