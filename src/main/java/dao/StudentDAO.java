package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import daoInterfaces.IStudentDAO;
import domain.Student;

public class StudentDAO extends GenericDAO<Student> implements IStudentDAO {

	public StudentDAO(Class<Student> cls) {
		super(cls);
	}

	@Override
	public List<Student> getStudentsByGroupId(int groupId) {
		List<Student> lst = null;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lst = session.createNativeQuery("SELECT * FROM student WHERE groupID=" + groupId + " ORDER BY student.lastName", Student.class).getResultList();
			t.commit();
		} catch (HibernateException | NoResultException e) {
			t.rollback();
			return null;
		}
		return lst;
	}

}
