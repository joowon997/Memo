package com.nowjoo.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/memo/post")
@Controller
public class PostController {

	@GetMapping("/list-view")
	public String mempList(){
		
		return "post/list";
	}
	
}
