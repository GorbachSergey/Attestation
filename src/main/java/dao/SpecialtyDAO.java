package dao;

import daoInterfaces.ISpecialtyDAO;
import domain.Specialty;

public class SpecialtyDAO extends GenericDAO<Specialty> implements ISpecialtyDAO {

	public SpecialtyDAO(Class<Specialty> cls) {
		super(cls);
	}
}
