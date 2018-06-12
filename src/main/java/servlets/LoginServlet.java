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
import daoInterfaces.IInstitutDAO;
import daoInterfaces.ITeacherDAO;
import domain.Institut;
import domain.Teacher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter("login").trim();
		String password = request.getParameter("password").trim();
		DAOFactory factory = new DAOFactory();
		ITeacherDAO teacherDAO = factory.getTeacherDAO();
		teacherDAO.openSession();
		Teacher user = teacherDAO.getTeacherByLoginAndPassword(login, password);
		teacherDAO.closeSession();
		if (user != null) {
			IInstitutDAO institutDAO = factory.getInstitutDAO();
			institutDAO.openSession();
			List<Institut> instituts = institutDAO.getAll();
			institutDAO.closeSession();
			session.setAttribute("user", user);
			request.setAttribute("list", instituts);
			request.getRequestDispatcher("private/showTable.jsp").forward(request, response);
		} else {
			request.setAttribute("loginError", "Логін або пароль введено не вірно! Спробуйте ще раз.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
