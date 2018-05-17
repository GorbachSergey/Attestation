package dao;

import daoInterfaces.IFacultyDAO;
import domain.Faculty;

public class FacultyDAO extends GenericDAO<Faculty> implements IFacultyDAO {

	public FacultyDAO(Class<Faculty> cls) {
		super(cls);
	}

}
