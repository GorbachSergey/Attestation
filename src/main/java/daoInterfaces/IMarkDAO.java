package daoInterfaces;

import domain.Mark;

public interface IMarkDAO extends IGenericDAO<Mark> {

	Mark getMarkBySubjectAndStudent(int subjectId, int studentId);

}
