package com.cdac.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class_details")
public class Class {
	
	@Id
	@Column(name = "class_id")
	private int class_id;
	@Column(name = "class_name")
	private String class_name;

	 @OneToMany(cascade = CascadeType.REMOVE)
	 @JoinColumn(referencedColumnName="class_id")
	 private List<Subject> subjects;
	
	 @OneToMany(cascade = CascadeType.REMOVE) 
	 @JoinColumn(referencedColumnName="class_id")
	 private List<User> user;
	 

	 


	public Class(){
		super();
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	  public List<User> getStudent() { 
		  return user;
		  }
	  
	  public void setStudent(List<User> student)
	  {
		  this.user = user;
	   }
	 

	
	

}
