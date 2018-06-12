package dao;

import daoInterfaces.IDAOFactory;
import daoInterfaces.IFacultyDAO;
import daoInterfaces.IGroupDAO;
import daoInterfaces.IInstitutDAO;
import daoInterfaces.IKafedraDAO;
import daoInterfaces.IMarkDAO;
import daoInterfaces.ISpecialtyDAO;
import daoInterfaces.IStudentDAO;
import daoInterfaces.ISubjectDAO;
import daoInterfaces.ITeacherDAO;
import domain.Faculty;
import domain.Group;
import domain.Institut;
import domain.Kafedra;
import domain.Mark;
import domain.Specialty;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public class DAOFactory implements IDAOFactory {

	public DAOFactory() {

	}

	public IInstitutDAO getInstitutDAO() {
		return new InstitutDAO(Institut.class);
	}

	public IFacultyDAO getFacultyDAO() {
		return new FacultyDAO(Faculty.class);
	}

	public ISpecialtyDAO getSpecialtyDAO() {
		return new SpecialtyDAO(Specialty.class);
	}

	public IGroupDAO getGroupDAO() {
		return new GroupDAO(Group.class);
	}

	public IStudentDAO getStudentDAO() {
		return new StudentDAO(Student.class);
	}

	public IMarkDAO getMarkDAO() {
		return new MarkDAO(Mark.class);
	}

	public ISubjectDAO getSubjectDAO() {
		return new SubjectDAO(Subject.class);
	}

	public ITeacherDAO getTeacherDAO() {
		return new TeacherDAO(Teacher.class);
	}

	public IKafedraDAO getKafedraDAO() {
		return new KafedraDAO(Kafedra.class);
	}

}
