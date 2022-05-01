package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Class;
import com.cdac.repository.ClassRepository;


@Service
@Transactional
public class ClassService {
	
	@Autowired
	private ClassRepository cRepository;
	
	public void save(Class classess) {
		cRepository.save(classess);
    }
	public List<Class> listAll() {
        return cRepository.findAll();
    }
	public void delete(int id) {
        cRepository.deleteById(id);
    }
	 public Class get(int id) {
	        return cRepository.findById(id).get();
	    }

}
