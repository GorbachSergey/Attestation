package dao;

import daoInterfaces.ITeacherDAO;
import domain.Teacher;

public class TeacherDAO extends GenericDAO<Teacher> implements ITeacherDAO {

	public TeacherDAO(Class<Teacher> cls) {
		super(cls);
	}
}
