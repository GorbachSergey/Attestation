package servlets;

import java.io.IOException;
import java.util.List;

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
import domain.Course;
import domain.Faculty;
import domain.Group;
import domain.Institut;
import domain.Specialty;

@WebServlet("/SelectTableServlet")
public class SelectTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectTableServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("elemId").split("\\|")[0];
		int id = Integer.parseInt(request.getParameter("elemId").split("\\|")[1]);
		HttpSession session = request.getSession();
		DAOFactory factory = new DAOFactory();
		switch (tableName) {
		case "Institut":
			IInstitutDAO insDAO = factory.getInstitutDAO();
			insDAO.openSession();
			Institut ins = insDAO.getById(id);
			List<Faculty> faculties = ins.getFaculties();
			insDAO.closeSession();
			request.setAttribute("list", faculties);
			break;
		case "Faculty":
			IFacultyDAO facultyDAO = factory.getFacultyDAO();
			facultyDAO.openSession();
			Faculty f = facultyDAO.getById(id);
			List<Specialty> specialties = f.getSpecialties();
			facultyDAO.closeSession();
			request.setAttribute("list", specialties);
			break;
		case "Specialty":
			session.setAttribute("specId", id);
			List<Course> courses = Course.getCources();
			request.setAttribute("list", courses);
			break;
		case "Course":
			int specId = (int) session.getAttribute("specId");
			IGroupDAO groupDAO = factory.getGroupDAO();
			groupDAO.openSession();
			List<Group> groups = groupDAO.getGroupsBySpecialtyAndCourse(specId, id);
			groupDAO.closeSession();
			request.setAttribute("list", groups);
			break;
		default:
			break;
		}
		request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
