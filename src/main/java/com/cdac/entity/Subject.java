package com.cdac.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject_details")
public class Subject {
	
	@Id
	@Column(name = "subject_id")
	private int subject_id;
	@Column(name = "subject_name")
	private String subject_name;
	
	@ManyToOne
	private Class classes;
	 
	public Subject() {
		super();
	}
	

	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}


	public Class getClasses() {
		return classes;
	}


	public void setClasses(Class classes) {
		this.classes = classes;
	}

	
}
