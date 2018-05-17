package dao;

import daoInterfaces.IStudentDAO;
import domain.Student;

public class StudentDAO extends GenericDAO<Student> implements IStudentDAO {

	public StudentDAO(Class<Student> cls) {
		super(cls);
	}
}
