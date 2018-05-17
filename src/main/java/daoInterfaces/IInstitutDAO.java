package daoInterfaces;

import domain.Institut;

public interface IInstitutDAO extends IGenericDAO<Institut> {

	Institut getIstitutByName(String name);

	void deleteInstituteByName(String name);
}
