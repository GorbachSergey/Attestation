package dao;

import daoInterfaces.ISubjectDAO;
import domain.Subject;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO {

	public SubjectDAO(Class<Subject> cls) {
		super(cls);
	}

}
