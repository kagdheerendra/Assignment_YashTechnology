package com.yash.springorm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.yash.springorm.entity.Student;

@Transactional
public class StudentDao {
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public int insert(Student s) {
		return (Integer) this.template.save(s);
	}
	
	@Transactional
	public void update(Student s) {
		this.template.update(s);
	}
	
	@Transactional
	public void delete(int id) {
		Student s = this.template.get(Student.class, id);
		this.template.delete(s);
	}
	
	@Transactional
	public Student getStudentById(int id) {
		Student s = this.template.get(Student.class, id);
		return s;
	}
	
	public List<Student> getAllStudent() {
		return this.template.loadAll(Student.class);
	}
}
