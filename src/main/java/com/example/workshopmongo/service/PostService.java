package com.example.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post nao encontrado"));
	}
}
