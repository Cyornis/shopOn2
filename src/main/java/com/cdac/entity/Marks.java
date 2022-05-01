package com.cdac.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Marks {
	@Id
	private int marks_id;
	private int marks_obtained;
	
	@OneToOne
	private Subject subject;
	public int getMarks_id() {
		return marks_id;
	}
	public void setMarks_id(int marks_id) {
		this.marks_id = marks_id;
	}
	public int getMarks_obtained() {
		return marks_obtained;
	}
	public void setMarks_obtained(int marks_obtained) {
		this.marks_obtained = marks_obtained;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

}
