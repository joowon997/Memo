package com.nowjoo.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	// ORDER BY `id` DESC
	public List<Post> findAllByOrderByIdDesc();

	// WHERE userId = session.getAttribute("userId")
	public List<Post> findByUserIdOrderByIdDesc(int userId);
	
	
}
