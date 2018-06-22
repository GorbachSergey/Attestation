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
import daoInterfaces.IKafedraDAO;
import daoInterfaces.ISubjectDAO;
import daoInterfaces.ITeacherDAO;
import domain.Faculty;
import domain.Kafedra;
import domain.Subject;
import domain.Teacher;

@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OperationServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		HttpSession session = request.getSession();
		DAOFactory factory = (DAOFactory) session.getAttribute("factory");
		if (op.equals("1")) {
			session.setAttribute("tableName", "Teacher");
			ITeacherDAO teacherDAO = factory.getTeacherDAO();
			teacherDAO.openSession();
			List<Teacher> teachers = teacherDAO.getAll();
			teacherDAO.closeSession();
			request.setAttribute("lst", teachers);
			IKafedraDAO kafedraDAO = factory.getKafedraDAO();
			kafedraDAO.openSession();
			List<Kafedra> lst = kafedraDAO.getAll();
			request.setAttribute("lst1", lst);
			kafedraDAO.closeSession();
			request.getRequestDispatcher("editTeacher.jsp").forward(request, response);
		} else if (op.equals("2")) {
			session.setAttribute("tableName", "Kafedra");
			IKafedraDAO kafedraDAO = factory.getKafedraDAO();
			kafedraDAO.openSession();
			List<Kafedra> lst = kafedraDAO.getAll();
			request.setAttribute("lst", lst);
			kafedraDAO.closeSession();
			IFacultyDAO facultyDAO = factory.getFacultyDAO();
			facultyDAO.openSession();
			List<Faculty> lst1 = facultyDAO.getAll();
			facultyDAO.closeSession();
			request.setAttribute("lst1", lst1);
			request.getRequestDispatcher("editKafedra.jsp").forward(request, response);
		} else {
			session.setAttribute("tableName", "Dependency");
			ITeacherDAO teacherDAO = factory.getTeacherDAO();
			teacherDAO.openSession();
			List<Teacher> lstTeacher = teacherDAO.getAll();

			ISubjectDAO subDAO = factory.getSubjectDAO();
			subDAO.openSession();
			List<Subject> lst1 = subDAO.getAll();
			subDAO.closeSession();
			request.setAttribute("lst", lstTeacher);
			request.setAttribute("lst1", lst1);
			request.getRequestDispatcher("editDependency.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
