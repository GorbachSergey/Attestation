package daoInterfaces;

import java.util.List;

import domain.Student;

public interface IStudentDAO extends IGenericDAO<Student> {

	List<Student> getStudentsByGroupId(int groupId);

}
