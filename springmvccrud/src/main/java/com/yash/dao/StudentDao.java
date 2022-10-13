package com.yash.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.yash.beans.Student;

public class StudentDao {
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Student s) {
		String sql = "insert into student(name,marks,department) values('" + s.getName() + "'," + s.getMarks() + ",'"
				+ s.getDepartment() + "')";
		return template.update(sql);
	}

	public int update(Student s) {
		String sql = "update student set name='" + s.getName() + "', marks=" + s.getMarks() + ",department='"
				+ s.getDepartment() + "' where id=" + s.getId() + "";
		return template.update(sql);
	}

	public int delete(int id) {
		String sql = "delete from student where id=" + id + "";
		return template.update(sql);
	}

	@SuppressWarnings("deprecation")
	public Student getStudentById(int id) {
		String sql = "select * from student where id=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> getStudents() {
		return template.query("select * from student order by marks desc", new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int row) throws SQLException {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setMarks(rs.getDouble(3));
                s.setDepartment(rs.getString(4));
				return s;
			}
		});
	}
}
