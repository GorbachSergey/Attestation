package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.IGroupDAO;
import domain.Group;

public class GroupDAO extends GenericDAO<Group> implements IGroupDAO {
	public GroupDAO(Class<Group> cls) {
		super(cls);
	}

	@Override
	public List<Group> getGroupsBySpecialtyAndCourse(int spec, int id) {
		List<Group> lst = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lst = session.createNativeQuery("SELECT * FROM groups WHERE course =" + id + "  AND specialtyID =" + spec + " ORDER BY groups.name", Group.class).getResultList();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return lst;
	}

}
