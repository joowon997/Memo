package com.nowjoo.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.memo.post.domain.Post;
import com.nowjoo.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/memo/post")
@Controller
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/list-view")
	public String memoList(
			Model model
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postService.getPostList(userId);
		
		model.addAttribute("memoList", postList);
		
		return "post/list";
	}

	@GetMapping("/create-view")
	public String addMemo(){
		
		return "post/create";
	}

	@GetMapping("/detail-view")
	public String detailMemo(
			@RequestParam("id") int id
			, Model model){
		
		Post post = postService.getPost(id);
		
		model.addAttribute("memo", post);
		
		return "post/detail";
	}
	
}
