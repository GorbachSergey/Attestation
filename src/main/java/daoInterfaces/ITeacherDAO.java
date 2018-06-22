package daoInterfaces;

import java.util.List;

import domain.Teacher;

public interface ITeacherDAO extends IGenericDAO<Teacher> {

	public Teacher getTeacherByLoginAndPassword(String login, String password);

	public List<Teacher> getTeachersByDistribution();

	public void removeFromDependency(int id, int subId);

	public void insertIntoDependency(int teacherId, int subjectId);

}
