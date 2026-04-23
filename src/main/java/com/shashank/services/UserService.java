package com.shashank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shashank.models.User;
import com.shashank.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean register(User user) {
		if (userRepository.existsById(user.getEmail()))
			return false;

		userRepository.save(user);
		return true;
	}

	public User updateUser(User user) {
		User u = userRepository.findById(user.getEmail()).orElse(null);
		if (u != null) {
			u.setPhone(user.getPhone());
			u.setName(user.getName());
			u.setDob(user.getDob());
			u.setGender(user.getGender());
			userRepository.save(u);
		}
		return u;
	}

	public boolean updatePhoto(String email, byte[] photo) {
		User user = userRepository.findById(email).orElse(null);
		if (user == null)
			return false;

		user.setPhoto(photo);
		userRepository.save(user);
		return true;
	}

	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
		if (user != null && user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public boolean updatePassword(String email, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
		if (user != null) {
			user.setPassword(newPassword);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public User getUser(String email) {
		return userRepository.findById(email).orElse(null);
	}

	public byte[] getPhoto(String email) {
		User user = userRepository.findById(email).orElse(null);
		return user != null ? user.getPhoto() : null;
	}
}

