package com.nowjoo.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.memo.common.FileManager;
import com.nowjoo.memo.post.domain.Post;
import com.nowjoo.memo.post.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	// 메모 삭제
	public boolean deletePost(int id){
		Optional<Post> optionalPsot = postRepository.findById(id);
	
		Post post = optionalPsot.orElse(null);
		
		if(post != null) {
			FileManager.removeFile(post.getImagePath());
			postRepository.delete(post);
			return true;
		}else {
			return false;
		}
	}
	
	
	// 메모 수정
	public Post updatePost(int id, String title, String contents){
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		if (post != null) {
			Post updatePost = post.toBuilder()
								.title(title)
								.contents(contents)
								.build();
			return postRepository.save(updatePost);
		} else {
			return null;
		}
		
	}
	
	// 메모 추가
	public Post addPost(int userId, String title, String contents, MultipartFile file){
		
		String urlPath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
					.userId(userId)
					.title(title)
					.contents(contents)
					.imagePath(urlPath)
					.build();
		
		Post result = postRepository.save(post);
		
		return result;
	}
	
	// 로그인한 유저 메모 전체 조회
	public List<Post> getPostList(int userId){
		
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	// 메모 조회
	public Post getPost(int id){
		Optional<Post> optionalPost = postRepository.findById(id);
	
		Post post = optionalPost.orElse(null);
		
		return post;
	}
	
	
}
