package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.User;

public interface User_detailsRepository extends JpaRepository<User, Integer> {

	@Query("select i from User i where i.email=?1")
	public User findByEmails(@Param("email") String email);

	@Query("select n from User n where n.fName LIKE %?1%")
	public List<User> findAll(String name);

	public User findByEmailAndPassword(String email, String password);
	//public Student findByEmail(String email);
	
	
}
