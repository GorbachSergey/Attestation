package daoInterfaces;

import java.util.List;

import domain.Subject;

public interface ISubjectDAO extends IGenericDAO<Subject> {
	List<Subject> getSubjectsBySpecCourseAndTeacher(int teacherId, int specialtyId, int courseId);
}
