package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.ISubjectDAO;
import domain.Subject;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO {

	public SubjectDAO(Class<Subject> cls) {
		super(cls);
	}

	@Override
	public List<Subject> getSubjectsBySpecCourseAndTeacher(int teacherId, int specialtyId, int courseId) {
		List<Subject> lst = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lst = session.createNativeQuery("SELECT subject.* FROM subject,distribution WHERE subject.course=" + courseId + " AND subject.specialtyID=" + specialtyId + " AND distribution.teacherID =" + teacherId, Subject.class).getResultList();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return lst;
	}

}
