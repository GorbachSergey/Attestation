package dao;

import daoInterfaces.IInstitutDAO;
import domain.Institut;

public class InstitutDAO extends GenericDAO<Institut> implements IInstitutDAO {

	public InstitutDAO(Class<Institut> cls) {
		super(cls);
	}

	public Institut getIstitutByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteInstituteByName(String name) {
		// TODO Auto-generated method stub
	}

}
