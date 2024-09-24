package com.nowjoo.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.memo.post.domain.Post;
import com.nowjoo.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/memo/post")
public class PostRestController {

	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	// 메모 삭제
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(
			@RequestParam("id") int id){
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (postService.deletePost(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
	
	// 메모 수정
	@PutMapping("/update")
	public Map<String, String> updateMemo(
			@RequestParam("id") int id
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents){
		
		Post post = postService.updatePost(id, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (post != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
	
	// 메모생성
	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value = "imageFile", required = false) MultipartFile file
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Post post = postService.addPost(userId, title, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (post != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
