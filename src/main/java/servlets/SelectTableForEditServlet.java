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
import daoInterfaces.IInstitutDAO;
import domain.Faculty;
import domain.Institut;

@WebServlet("/SelectTableForEditServlet")
public class SelectTableForEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectTableForEditServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		HttpSession session = request.getSession();
		DAOFactory factory = new DAOFactory();
		switch (tableName) {
		case "Institut":
			IInstitutDAO insDAO = factory.getInstitutDAO();
			insDAO.openSession();
			List<Institut> instituts = insDAO.getAll();
			insDAO.closeSession();
			request.setAttribute("lst", instituts);
			break;
		case "Faculty":
			int idF = Integer.parseInt(request.getParameter("id"));
			IFacultyDAO facultyDAO = factory.getFacultyDAO();
			facultyDAO.openSession();
			Faculty f = facultyDAO.getById(idF);
			facultyDAO.closeSession();
			Institut i = f.getInstitut();
			List<Faculty> faculties = i.getFaculties();
			IInstitutDAO institutDAO = factory.getInstitutDAO();
			institutDAO.openSession();
			List<Institut> ins = institutDAO.getAll();
			institutDAO.closeSession();
			request.setAttribute("lst", faculties);
			request.setAttribute("lst1", ins);
			break;
		}

		request.getRequestDispatcher("edit" + tableName + ".jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
