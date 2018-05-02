package com.doctoriales.mi.dao;

import java.util.List;

import com.doctoriales.mi.model.Student;
import com.doctoriales.mi.model.Teacher;
import com.doctoriales.mi.model.User;

public interface TeacherDAO {
	public int saveOrUpdate(Teacher contact);/*1=Update Success, 2=Update failed, 3=Insertion Success and 
	4=Insertion Failed*/

	public void delete(int inscriptionCode);

	public Teacher get(int inscriptionCode);
	public Teacher getByMail(String email);
	public List<Teacher> list();
}
