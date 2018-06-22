package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.ISpecialtyDAO;
import domain.Specialty;

public class SpecialtyDAO extends GenericDAO<Specialty> implements ISpecialtyDAO {

	public SpecialtyDAO(Class<Specialty> cls) {
		super(cls);
	}

	@Override
	public List<Specialty> getSpecialtiesByFacultyId(int id) {
		List<Specialty> lst = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lst = session.createNativeQuery("SELECT * FROM specialty s WHERE s.facultyID = " + id, Specialty.class).getResultList();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return lst;
	}

}
