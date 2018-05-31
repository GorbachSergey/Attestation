package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import daoInterfaces.IGenericDAO;
import domain.IEntity;

public class GenericDAO<T extends IEntity> implements IGenericDAO<T> {

	protected Session session;
	protected Class<T> cls;

	public GenericDAO(Class<T> cls) {
		this.cls = cls;
	}

	public void save(T item) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.save(item);
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
	}

	public void update(T item) {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.update(item);
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
	}

	public T getById(int id) {
		T res = null;
		try {
			res = (T) session.get(this.cls, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return res;

	}

	public List<T> getAll() {
		List<T> lst = new ArrayList<T>();
		try {
			CriteriaQuery<T> cq = session.getCriteriaBuilder().createQuery(cls);
			Root<T> root = cq.from(cls);
			cq.select(root);
			lst = session.createQuery(cq).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return lst;
	}

	public void delete(T item) {
		Transaction t = null;
		try {

			t = session.beginTransaction();
			session.delete(item);
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}
	}

	/*
	 * private boolean exist(T obj) { Class<?> clazz = obj.getClass(); List<?> list
	 * = getObjectList(clazz); if (list != null && list.size() != 0) for (Object
	 * current : list) if (current.equals(obj)) return true; return false; }
	 */

	public void openSession() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void closeSession() {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}
