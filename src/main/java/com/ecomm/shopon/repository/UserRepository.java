package com.ecomm.shopon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Student;

public interface UserRepository extends JpaRepository<Student, Integer> {

	@Query("select i from Student i where i.email=?1")
	public Student findByEmails(@Param("email") String email);

	@Query("select n from Student n where n.fName LIKE %?1%")
	public List<Student> findAll(String name);

	public Student findByEmailAndPassword(String email, String password);
	//public Student findByEmail(String email);
	
	
}
