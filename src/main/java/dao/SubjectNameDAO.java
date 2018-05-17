package dao;

import daoInterfaces.ISubjectNameDAO;
import domain.Subjectname;

public class SubjectNameDAO extends GenericDAO<Subjectname> implements ISubjectNameDAO {

	public SubjectNameDAO(Class<Subjectname> cls) {
		super(cls);
	}

}
