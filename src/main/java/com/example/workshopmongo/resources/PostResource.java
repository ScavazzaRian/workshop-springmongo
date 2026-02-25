package com.example.workshopmongo.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.service.PostService;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	@Autowired
	private PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> list = postService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = postService.findById(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam String text) {
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<Post>> search(@RequestParam String text,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate min,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate max) {
		List<Post> list = postService.findSearch(text, min, max);
		return ResponseEntity.ok(list);
	}
}
