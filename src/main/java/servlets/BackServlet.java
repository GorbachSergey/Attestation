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
import domain.Institut;

@WebServlet("/BackServlet")
public class BackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BackServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("back");
		HttpSession session = request.getSession();
		DAOFactory factory = (DAOFactory) session.getAttribute("factory");
		switch (name) {
		case "Institut":
			session.setAttribute("tableName", "Institut");
			session.setAttribute("tableNameRU", "Інститут");
			IInstitutDAO institutDAO = factory.getInstitutDAO();
			institutDAO.openSession();
			List<Institut> instituts = institutDAO.getAll();
			institutDAO.closeSession();
			request.setAttribute("list", instituts);
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
