package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Subject;
import com.cdac.repository.SubjectRepository;

@Service
@Transactional
public class SubjectService {
	
	@Autowired
	private SubjectRepository sRepository;
	
	public void save(Subject subject) {
		sRepository.save(subject);
    }
	public List<Subject> listAll() {
        return sRepository.findAll();
    }
	public void delete(int id) {
        sRepository.deleteById(id);
    }
	 public Subject get(int id) {
	        return sRepository.findById(id).get();
	    }

}
