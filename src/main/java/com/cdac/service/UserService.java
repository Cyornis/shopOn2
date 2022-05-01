package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.User;
import com.cdac.repository.User_detailsRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private User_detailsRepository uRepository;
	
	public void save(User user_details) {
		uRepository.save(user_details);
    }
	public List<User> listAll(String name) {
		if(name != null) {
			return uRepository.findAll(name);
		}
        return uRepository.findAll();
    }
	public void delete(int id) {
        uRepository.deleteById(id);
    }
	 public User get(int id) {
	        return uRepository.findById(id).get();
	}
	 
	 
	 public User findByEmailAndPassword(String email, String password) {
			User user = null;
			if (email != null && password != null) {
				user = uRepository.findByEmailAndPassword(email, password);
			}
			return user;
		}
	 
	 public User logins(String email) {
			User user=uRepository.findByEmails(email);
			return user;
	}
}
