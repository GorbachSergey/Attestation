package servlets;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import daoInterfaces.IFacultyDAO;
import daoInterfaces.IGroupDAO;
import daoInterfaces.IMarkDAO;
import daoInterfaces.ISpecialtyDAO;
import daoInterfaces.IStudentDAO;
import daoInterfaces.ISubjectDAO;
import domain.Course;
import domain.Faculty;
import domain.Group;
import domain.Mark;
import domain.Specialty;
import domain.Student;
import domain.Subject;
import domain.Teacher;

@WebServlet("/SelectTableServlet")
public class SelectTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectTableServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("elemId").split("\\|")[0];
		int id = Integer.parseInt(request.getParameter("elemId").split("\\|")[1]);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		Teacher user = (Teacher) session.getAttribute("user");
		DAOFactory factory = (DAOFactory) session.getAttribute("factory");
		switch (tableName) {
		case "Institut":
			session.setAttribute("tableName", "Faculty");
			session.setAttribute("tableNameRU", "Факультет");
			IFacultyDAO facultyDAO = factory.getFacultyDAO();
			facultyDAO.openSession();
			List<Faculty> faculties = facultyDAO.getFacultiesByInstitutId(id);
			request.setAttribute("list", faculties);
			break;
		case "Faculty":
			session.setAttribute("tableName", "Specialty");
			session.setAttribute("tableNameRU", "Спеціальність");
			ISpecialtyDAO specialtyDAO = factory.getSpecialtyDAO();
			specialtyDAO.openSession();
			List<Specialty> specialties = specialtyDAO.getSpecialtiesByFacultyId(id);
			specialtyDAO.closeSession();
			request.setAttribute("list", specialties);
			break;
		case "Specialty":
			session.setAttribute("tableNameRU", "Курс");
			session.setAttribute("tableName", "Course");
			session.setAttribute("specId", id);
			List<Course> courses = Course.getCourses();
			request.setAttribute("list", courses);
			break;
		case "Course":
			session.setAttribute("tableNameRU", "Група");
			session.setAttribute("tableName", "Group");
			session.setAttribute("courseId", id);
			int specId = (int) session.getAttribute("specId");
			IGroupDAO groupDAO = factory.getGroupDAO();
			groupDAO.openSession();
			List<Group> groups = groupDAO.getGroupsBySpecialtyAndCourse(specId, id);
			groupDAO.closeSession();
			request.setAttribute("list", groups);
			break;
		case "Group":
			session.setAttribute("tableNameRU", "Предмет");
			session.setAttribute("tableName", "Subject");
			session.setAttribute("groupId", id);
			Teacher t = (Teacher) session.getAttribute("user");
			int spId = (int) session.getAttribute("specId");
			int courseId = (int) session.getAttribute("courseId");
			List<Subject> subjects = null;
			ISubjectDAO subDAO = factory.getSubjectDAO();
			subDAO.openSession();
			if (t.getLastName().equals("admin")) {
				subjects = subDAO.getSubjectsBySpecIdAndCourse(spId, courseId);
			} else {
				subjects = subDAO.getSubjectsBySpecCourseAndTeacher(t.getId(), spId, courseId);
			}
			subDAO.closeSession();
			request.setAttribute("list", subjects);
			break;
		case "Subject":
			session.setAttribute("tableName", "Student");
			session.setAttribute("subjectId", id);
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
			for (Student s : students) {
				Mark m = markDAO.getMarkBySubjectAndStudent(id, s.getId());
				map.put(s, m);
			}
			markDAO.closeSession();
			request.setAttribute("list", map);
			break;
		}

		if (tableName.equals("Subject") && user.getLastName().equals("admin")) {
			request.getRequestDispatcher("editStudent.jsp").forward(request, response);
		} else if (tableName.equals("Subject") && !user.getLastName().equals("admin")) {
			request.getRequestDispatcher("showStudents.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
