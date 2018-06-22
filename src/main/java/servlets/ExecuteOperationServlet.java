package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import daoInterfaces.IFacultyDAO;
import daoInterfaces.IGroupDAO;
import daoInterfaces.IInstitutDAO;
import daoInterfaces.IKafedraDAO;
import daoInterfaces.ISpecialtyDAO;
import daoInterfaces.IStudentDAO;
import daoInterfaces.ISubjectDAO;
import daoInterfaces.ITeacherDAO;
import domain.Faculty;
import domain.Group;
import domain.Institut;
import domain.Kafedra;
import domain.Specialty;
import domain.Student;
import domain.Subject;
import domain.Teacher;
import util.ShowTable;

@WebServlet("/ExecuteOperationServlet")
public class ExecuteOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteOperationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String tableName = request.getParameter("tableName");
		DAOFactory factory = (DAOFactory) session.getAttribute("factory");
		if (request.getParameter("delete") != null) {
			int id = Integer.parseInt(request.getParameter("delete"));
			switch (tableName) {
			case "Institut":
				IInstitutDAO insDAO = factory.getInstitutDAO();
				insDAO.openSession();
				Institut ins = insDAO.getById(id);
				if (ins != null)
					insDAO.delete(ins);
				insDAO.closeSession();
				break;
			case "Faculty":
				IFacultyDAO facDAO = factory.getFacultyDAO();
				facDAO.openSession();
				Faculty f = facDAO.getById(id);
				if (f != null)
					facDAO.delete(f);
				facDAO.closeSession();
				break;
			case "Specialty":
				ISpecialtyDAO specDAO = factory.getSpecialtyDAO();
				specDAO.openSession();
				Specialty s = specDAO.getById(id);
				if (s != null)
					specDAO.delete(s);
				specDAO.closeSession();
				break;
			case "Group":
				IGroupDAO groupDAO = factory.getGroupDAO();
				groupDAO.openSession();
				Group g = groupDAO.getById(id);
				if (g != null)
					groupDAO.delete(g);
				groupDAO.closeSession();
				break;
			case "Subject":
				ISubjectDAO subjectDAO = factory.getSubjectDAO();
				subjectDAO.openSession();
				Subject sub = subjectDAO.getById(id);
				if (sub != null)
					subjectDAO.delete(sub);
				subjectDAO.closeSession();
				break;
			case "Kafedra":
				IKafedraDAO kafedraDAO = factory.getKafedraDAO();
				kafedraDAO.openSession();
				Kafedra kaf = kafedraDAO.getById(id);
				if (kaf != null)
					kafedraDAO.delete(kaf);
				kafedraDAO.closeSession();
				break;
			case "Teacher":
				ITeacherDAO teacherDAO = factory.getTeacherDAO();
				teacherDAO.openSession();
				Teacher t = teacherDAO.getById(id);
				if (t != null)
					teacherDAO.delete(t);
				teacherDAO.closeSession();
				break;
			case "Dependency":
				int subId = Integer.parseInt(request.getParameter("teacher" + id));
				ITeacherDAO teacheDAO = factory.getTeacherDAO();
				teacheDAO.closeSession();
				teacheDAO.openSession();
				teacheDAO.removeFromDependency(id, subId);
				teacheDAO.closeSession();
				break;
			case "Student":
				IStudentDAO studentDAO = factory.getStudentDAO();
				studentDAO.openSession();
				Student student = studentDAO.getById(id);
				if (student != null)
					studentDAO.delete(student);
				studentDAO.closeSession();
				break;
			}
		} else if (request.getParameter("add") != null) {
			switch (tableName) {
			case "Institut":
				String name = request.getParameter("nameIns");
				Institut ins = new Institut(name);
				IInstitutDAO insDAO = factory.getInstitutDAO();
				insDAO.openSession();
				insDAO.save(ins);
				insDAO.closeSession();
				break;
			case "Faculty":
				String nameFaculty = request.getParameter("nameFac");
				int insId = Integer.parseInt(request.getParameter("nameIns"));
				IInstitutDAO institutDAO = factory.getInstitutDAO();
				institutDAO.openSession();
				Institut i = institutDAO.getById(insId);
				institutDAO.closeSession();
				Faculty f = new Faculty(nameFaculty, i);
				IFacultyDAO facDAO = factory.getFacultyDAO();
				facDAO.openSession();
				facDAO.save(f);
				facDAO.closeSession();
				break;
			case "Specialty":
				String nameSpec = request.getParameter("nameSpec");
				String code = request.getParameter("codeSpec");
				int facId = Integer.parseInt(request.getParameter("nameFac"));
				IFacultyDAO facultyDAO = factory.getFacultyDAO();
				facultyDAO.openSession();
				Faculty fa = facultyDAO.getById(facId);
				facultyDAO.closeSession();
				Specialty s = new Specialty(nameSpec, code, fa);
				ISpecialtyDAO specDAO = factory.getSpecialtyDAO();
				specDAO.openSession();
				specDAO.save(s);
				specDAO.closeSession();
				break;
			case "Group":
				String nameGroup = request.getParameter("nameGroup");
				int courseGroup = Integer.parseInt(request.getParameter("courseGroup"));
				int idSpec = Integer.parseInt(request.getParameter("nameSpec"));
				ISpecialtyDAO specialtyDAO = factory.getSpecialtyDAO();
				specialtyDAO.openSession();
				Specialty spec = specialtyDAO.getById(idSpec);
				specialtyDAO.closeSession();
				Group g = new Group(nameGroup, courseGroup, spec);
				IGroupDAO groupDAO = factory.getGroupDAO();
				groupDAO.openSession();
				groupDAO.save(g);
				groupDAO.closeSession();
				break;
			case "Subject":
				String nameSub = request.getParameter("nameSub");
				int courseSub = Integer.parseInt(request.getParameter("courseSub"));
				int idS = Integer.parseInt(request.getParameter("nameSpec"));
				ISpecialtyDAO specialtDAO = factory.getSpecialtyDAO();
				specialtDAO.openSession();
				Specialty sp = specialtDAO.getById(idS);
				specialtDAO.closeSession();
				Subject sub = new Subject();
				sub.setName(nameSub);
				sub.setCourse(courseSub);
				sub.setSpecialty(sp);
				ISubjectDAO subjectDAO = factory.getSubjectDAO();
				subjectDAO.openSession();
				subjectDAO.save(sub);
				subjectDAO.closeSession();
				break;
			case "Kafedra":
				String nameKaf = request.getParameter("nameKaf");
				String phone = request.getParameter("phoneKaf");
				int idFaculty = Integer.parseInt(request.getParameter("nameFac"));
				IFacultyDAO facultDAO = factory.getFacultyDAO();
				facultDAO.openSession();
				Faculty faculty = facultDAO.getById(idFaculty);
				facultDAO.closeSession();
				Kafedra kafedra = new Kafedra();
				kafedra.setName(nameKaf);
				kafedra.setPhone(phone);
				kafedra.setFaculty(faculty);
				IKafedraDAO kafedraDAO = factory.getKafedraDAO();
				kafedraDAO.openSession();
				kafedraDAO.save(kafedra);
				kafedraDAO.closeSession();
				break;
			case "Teacher":
				String lastName = request.getParameter("lastName");
				String firstName = request.getParameter("firstName");
				String middleName = request.getParameter("middleName");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				int idKafedra = Integer.parseInt(request.getParameter("nameKaf"));
				IKafedraDAO kafDAO = factory.getKafedraDAO();
				kafDAO.openSession();
				Kafedra k = kafDAO.getById(idKafedra);
				kafDAO.closeSession();
				Teacher teacher = new Teacher();
				teacher.setFirstName(firstName);
				teacher.setLastName(lastName);
				teacher.setMiddleName(middleName);
				teacher.setLogin(login);
				teacher.setPassword(password);
				teacher.setKafedra(k);
				ITeacherDAO teacherDAO = factory.getTeacherDAO();
				teacherDAO.openSession();
				teacherDAO.save(teacher);
				teacherDAO.closeSession();
				break;
			case "Dependency":
				int teacherId = Integer.parseInt(request.getParameter("teacher"));
				int subjectId = Integer.parseInt(request.getParameter("subject"));
				ITeacherDAO teacheDAO = factory.getTeacherDAO();
				teacheDAO.closeSession();
				teacheDAO.openSession();
				teacheDAO.insertIntoDependency(teacherId, subjectId);
				teacheDAO.closeSession();
				break;
			case "Student":
				String last = request.getParameter("lastName");
				String first = request.getParameter("firstName");
				String middle = request.getParameter("middleName");
				int idGroup = Integer.parseInt(request.getParameter("groupId"));
				IGroupDAO grDAO = factory.getGroupDAO();
				grDAO.openSession();
				Group group = grDAO.getById(idGroup);
				grDAO.closeSession();
				Student student = new Student();
				student.setFirstName(first);
				student.setLastName(last);
				student.setMiddleName(middle);
				student.setGroup(group);
				IStudentDAO studentDAO = factory.getStudentDAO();
				studentDAO.openSession();
				studentDAO.save(student);
				studentDAO.closeSession();
				break;
			}

		} else if (request.getParameter("edit") != null) {
			int id = Integer.parseInt(request.getParameter("edit"));
			switch (tableName) {
			case "Institut":
				String name = request.getParameter("nameIns" + id);
				IInstitutDAO insDAO = factory.getInstitutDAO();
				insDAO.openSession();
				Institut ins = insDAO.getById(id);
				ins.setName(name);
				insDAO.update(ins);
				insDAO.closeSession();
				break;
			case "Faculty":
				String nameFaculty = request.getParameter("nameFac" + id);
				int insId = Integer.parseInt(request.getParameter("nameIns" + id));
				IInstitutDAO institutDAO = factory.getInstitutDAO();
				institutDAO.openSession();
				Institut i = institutDAO.getById(insId);
				institutDAO.closeSession();
				IFacultyDAO facDAO = factory.getFacultyDAO();
				facDAO.openSession();
				Faculty f = facDAO.getById(id);
				f.setName(nameFaculty);
				f.setInstitut(i);
				facDAO.update(f);
				facDAO.closeSession();
				break;
			case "Specialty":
				String nameSpec = request.getParameter("nameSpec" + id);
				String code = request.getParameter("codeSpec" + id);
				int facId = Integer.parseInt(request.getParameter("nameFac" + id));
				IFacultyDAO facultyDAO = factory.getFacultyDAO();
				facultyDAO.openSession();
				Faculty fa = facultyDAO.getById(facId);
				facultyDAO.closeSession();
				ISpecialtyDAO specDAO = factory.getSpecialtyDAO();
				specDAO.openSession();
				Specialty s = specDAO.getById(id);
				s.setName(nameSpec);
				s.setCode(code);
				s.setFaculty(fa);
				specDAO.update(s);
				specDAO.closeSession();
				break;
			case "Group":
				String nameGroup = request.getParameter("nameGroup" + id);
				int courseGroup = Integer.parseInt(request.getParameter("course"));
				int idSpec = Integer.parseInt(request.getParameter("nameSpec" + id));
				ISpecialtyDAO specialtyDAO = factory.getSpecialtyDAO();
				specialtyDAO.openSession();
				Specialty spec = specialtyDAO.getById(idSpec);
				specialtyDAO.closeSession();
				IGroupDAO groupDAO = factory.getGroupDAO();
				groupDAO.openSession();
				Group g = groupDAO.getById(id);
				g.setName(nameGroup);
				g.setCourse(courseGroup);
				g.setSpecialty(spec);
				groupDAO.update(g);
				groupDAO.closeSession();
				break;
			case "Subject":
				String nameSub = request.getParameter("nameSub" + id);
				int courseSub = Integer.parseInt(request.getParameter("course"));
				int idSp = Integer.parseInt(request.getParameter("nameSpec" + id));
				ISpecialtyDAO specialtDAO = factory.getSpecialtyDAO();
				specialtDAO.openSession();
				Specialty sp = specialtDAO.getById(idSp);
				specialtDAO.closeSession();
				ISubjectDAO subjectDAO = factory.getSubjectDAO();
				subjectDAO.openSession();
				Subject sub = subjectDAO.getById(id);
				sub.setName(nameSub);
				sub.setCourse(courseSub);
				sub.setSpecialty(sp);
				subjectDAO.update(sub);
				subjectDAO.closeSession();
				break;
			case "Kafedra":
				String nameKaf = request.getParameter("nameKaf" + id);
				String phone = request.getParameter("phoneKaf" + id);
				int idFaculty = Integer.parseInt(request.getParameter("facId" + id));
				IFacultyDAO facultDAO = factory.getFacultyDAO();
				facultDAO.openSession();
				Faculty faculty = facultDAO.getById(idFaculty);
				facultDAO.closeSession();
				IKafedraDAO kafedraDAO = factory.getKafedraDAO();
				kafedraDAO.openSession();
				Kafedra kafedra = kafedraDAO.getById(id);
				kafedra.setName(nameKaf);
				kafedra.setPhone(phone);
				kafedra.setFaculty(faculty);
				kafedraDAO.update(kafedra);
				kafedraDAO.closeSession();
				break;
			case "Teacher":
				String lastName = request.getParameter("lastName" + id);
				String firstName = request.getParameter("firstName" + id);
				String middleName = request.getParameter("middleName" + id);
				String login = request.getParameter("login" + id);
				String password = request.getParameter("password" + id);
				int idKafedra = Integer.parseInt(request.getParameter("kafId" + id));
				IKafedraDAO kafDAO = factory.getKafedraDAO();
				kafDAO.openSession();
				Kafedra k = kafDAO.getById(idKafedra);
				kafDAO.closeSession();
				ITeacherDAO teacherDAO = factory.getTeacherDAO();
				teacherDAO.openSession();
				Teacher teacher = teacherDAO.getById(id);
				teacher.setFirstName(firstName);
				teacher.setLastName(lastName);
				teacher.setMiddleName(middleName);
				teacher.setLogin(login);
				teacher.setPassword(password);
				teacher.setKafedra(k);
				teacherDAO.update(teacher);
				teacherDAO.closeSession();
				break;
			case "Student":
				String last = request.getParameter("lastName" + id);
				String first = request.getParameter("firstName" + id);
				String middle = request.getParameter("middleName" + id);
				int idGroup = Integer.parseInt(request.getParameter("group"));
				IGroupDAO grDAO = factory.getGroupDAO();
				grDAO.openSession();
				Group group = grDAO.getById(idGroup);
				grDAO.closeSession();
				IStudentDAO studentDAO = factory.getStudentDAO();
				studentDAO.openSession();
				Student student = studentDAO.getById(id);
				student.setFirstName(first);
				student.setLastName(last);
				student.setMiddleName(middle);
				student.setGroup(group);
				studentDAO.update(student);
				studentDAO.closeSession();
				break;
			}
		}
		ShowTable.show(request, session, tableName, factory);

		request.getRequestDispatcher("edit" + tableName + ".jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
