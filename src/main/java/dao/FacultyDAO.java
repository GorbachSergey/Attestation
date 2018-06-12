package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.IFacultyDAO;
import domain.Faculty;

public class FacultyDAO extends GenericDAO<Faculty> implements IFacultyDAO {

	public FacultyDAO(Class<Faculty> cls) {
		super(cls);
	}

	@Override
	public List<Faculty> getFacultiesByInstitutId(int id) {
		List<Faculty> lst = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lst = session.createNativeQuery("SELECT * FROM faculty f WHERE f.institutID = " + id, Faculty.class).getResultList();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return lst;
	}

}
