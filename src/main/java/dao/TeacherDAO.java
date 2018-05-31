package dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import daoInterfaces.ITeacherDAO;
import domain.Teacher;

public class TeacherDAO extends GenericDAO<Teacher> implements ITeacherDAO {

	public TeacherDAO(Class<Teacher> cls) {
		super(cls);
	}

	public Teacher getTeacherByLoginAndPassword(String login, String password) {
		Transaction t = null;
		Teacher teacher = null;
		try {
			t = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Teacher> cq = builder.createQuery(Teacher.class);
			Root<Teacher> root = cq.from(Teacher.class);
			cq.select(root).where(builder.equal(root.get("login"), login), builder.equal(root.get("password"), password));
			Query<Teacher> q = session.createQuery(cq);
			teacher = q.getSingleResult();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return teacher;
	}
}
