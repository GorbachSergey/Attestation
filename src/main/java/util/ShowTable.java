package util;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
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

public class ShowTable {

	public static void show(HttpServletRequest request, HttpSession session, String tableName, DAOFactory factory) {
		switch (tableName) {
		case "Institut":
			IInstitutDAO insDAO = factory.getInstitutDAO();
			insDAO.openSession();
			List<Institut> instituts = insDAO.getAll();
			insDAO.closeSession();
			request.setAttribute("lst", instituts);
			break;
		case "Faculty":
			IFacultyDAO facultyDAO = factory.getFacultyDAO();
			facultyDAO.openSession();
			List<Faculty> faculties = facultyDAO.getFacultiesByInstitutId(Integer.parseInt(request.getParameter("id")));
			facultyDAO.closeSession();
			IInstitutDAO institutDAO = factory.getInstitutDAO();
			institutDAO.openSession();
			Institut i = institutDAO.getById(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("name", i.toString());
			List<Institut> ins = institutDAO.getAll();
			institutDAO.closeSession();
			request.setAttribute("lst", faculties);
			request.setAttribute("lst1", ins);
			break;
		case "Specialty":
			ISpecialtyDAO specDAO = factory.getSpecialtyDAO();
			specDAO.openSession();
			List<Specialty> specialties = specDAO.getSpecialtiesByFacultyId(Integer.parseInt(request.getParameter("id")));
			specDAO.closeSession();
			IFacultyDAO facDAO = factory.getFacultyDAO();
			facDAO.openSession();
			Faculty f = facDAO.getById(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("name", f.toString());
			List<Faculty> fac = facDAO.getFacultiesByInstitutId(f.getInstitut().getId());
			facDAO.closeSession();
			request.setAttribute("lst", specialties);
			request.setAttribute("lst1", fac);
			break;
		case "Group":
			int specId = (int) session.getAttribute("specId");
			int courseId = (int) session.getAttribute("courseId");
			IGroupDAO groupDAO = factory.getGroupDAO();
			groupDAO.openSession();
			List<Group> groups = groupDAO.getGroupsBySpecialtyAndCourse(specId, courseId);
			groupDAO.closeSession();
			ISpecialtyDAO specialtyDAO = factory.getSpecialtyDAO();
			specialtyDAO.openSession();
			Specialty sp = specialtyDAO.getById(specId);
			session.setAttribute("name", sp.toString());
			List<Specialty> spe = specialtyDAO.getSpecialtiesByFacultyId(sp.getFaculty().getId());
			specialtyDAO.closeSession();
			request.setAttribute("lst", groups);
			request.setAttribute("lst1", spe);
			break;
		case "Subject":
			int speId = (int) session.getAttribute("specId");
			int coursId = (int) session.getAttribute("courseId");
			ISubjectDAO subjectDAO = factory.getSubjectDAO();
			subjectDAO.openSession();
			List<Subject> subjects = subjectDAO.getSubjectsBySpecIdAndCourse(speId, coursId);
			subjectDAO.closeSession();
			ISpecialtyDAO spDAO = factory.getSpecialtyDAO();
			spDAO.openSession();
			Specialty s = spDAO.getById(speId);
			session.setAttribute("name", s.toString());
			List<Specialty> spec = spDAO.getSpecialtiesByFacultyId(s.getFaculty().getId());
			spDAO.closeSession();
			request.setAttribute("lst", subjects);
			request.setAttribute("lst1", spec);
			break;
		case "Kafedra":
			IKafedraDAO kafedraDAO = factory.getKafedraDAO();
			kafedraDAO.openSession();
			List<Kafedra> lst = kafedraDAO.getAll();
			request.setAttribute("lst", lst);
			kafedraDAO.closeSession();
			IFacultyDAO faculDAO = factory.getFacultyDAO();
			faculDAO.openSession();
			List<Faculty> lst1 = faculDAO.getAll();
			faculDAO.closeSession();
			request.setAttribute("lst1", lst1);
			break;
		case "Teacher":
			ITeacherDAO teacherDAO = factory.getTeacherDAO();
			teacherDAO.openSession();
			List<Teacher> teachers = teacherDAO.getAll();
			teacherDAO.closeSession();
			request.setAttribute("lst", teachers);
			IKafedraDAO kafedrDAO = factory.getKafedraDAO();
			kafedrDAO.openSession();
			List<Kafedra> k = kafedrDAO.getAll();
			request.setAttribute("lst1", k);
			kafedrDAO.closeSession();
			break;
		case "Dependency":
			ITeacherDAO teacheDAO = factory.getTeacherDAO();
			teacheDAO.openSession();
			List<Teacher> lstTeacher = teacheDAO.getAll();
			ISubjectDAO subDAO = factory.getSubjectDAO();
			subDAO.openSession();
			List<Subject> lst2 = subDAO.getAll();
			subDAO.closeSession();
			request.setAttribute("lst", lstTeacher);
			request.setAttribute("lst1", lst2);
			break;
		case "Student":
			int id = (int) session.getAttribute("subjectId");
			SortedMap<Student, Mark> map = new TreeMap<>();
			int groupId = (int) session.getAttribute("groupId");
			IGroupDAO gr = factory.getGroupDAO();
			gr.openSession();
			Group g = gr.getById(groupId);
			gr.closeSession();
			request.setAttribute("nameGroup", g.toString());
			IStudentDAO studentDAO = factory.getStudentDAO();
			IMarkDAO markDAO = factory.getMarkDAO();
			studentDAO.openSession();
			List<Student> students = studentDAO.getStudentsByGroupId(groupId);
			studentDAO.closeSession();
			markDAO.openSession();
			for (Student stud : students) {
				Mark m = markDAO.getMarkBySubjectAndStudent(id, stud.getId());
				map.put(stud, m);
			}
			markDAO.closeSession();
			request.setAttribute("list", map);
		}
	}
}
