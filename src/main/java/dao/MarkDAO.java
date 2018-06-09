package dao;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.IMarkDAO;
import domain.Mark;

public class MarkDAO extends GenericDAO<Mark> implements IMarkDAO {

	public MarkDAO(Class<Mark> cls) {
		super(cls);
	}

	@Override
	public Mark getMarkBySubjectAndStudent(int subjectId, int studentId) {
		Transaction t = null;
		Mark mark = null;
		try {
			t = session.beginTransaction();
			mark = session.createNativeQuery("SELECT * FROM mark WHERE subjectID =" + subjectId + "  AND studentID = " + studentId, Mark.class).getSingleResult();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return mark;
	}

}
