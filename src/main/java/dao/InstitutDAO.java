package dao;

import daoInterfaces.IInstitutDAO;
import domain.Institut;

public class InstitutDAO extends GenericDAO<Institut> implements IInstitutDAO {

	public InstitutDAO(Class<Institut> cls) {
		super(cls);
	}

	@Override
	public Institut getIstitutByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInstituteByName(String name) {
		// TODO Auto-generated method stub
	}

}
