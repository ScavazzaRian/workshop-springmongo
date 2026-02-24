package com.example.workshopmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		List<User> list = userRepository.findAll();
		return list;
	}

	public User findById(String id) {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}

	public User insert(User user) {
		return userRepository.save(user);
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public User update(String id, User user) {
		User entity = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
		updateDate(user, entity);
		return userRepository.save(entity);
	}

	private void updateDate(User old, User newUser) {
		if (old.getName() != null) {
			newUser.setName(old.getName());
		}
		if (old.getEmail() != null) {
			newUser.setEmail(old.getEmail());
		}
	}
}
