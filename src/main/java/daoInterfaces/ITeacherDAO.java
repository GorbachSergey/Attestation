package daoInterfaces;

import domain.Teacher;

public interface ITeacherDAO extends IGenericDAO<Teacher> {

	public Teacher getTeacherByLoginAndPassword(String login, String password);

}
