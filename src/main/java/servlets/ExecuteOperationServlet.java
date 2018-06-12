package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import daoInterfaces.IInstitutDAO;
import domain.Institut;

@WebServlet("/ExecuteOperationServlet")
public class ExecuteOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteOperationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tableName = request.getParameter("tableName");
		DAOFactory factory = new DAOFactory();
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
			}
		} else if (request.getParameter("add") != null) {
			switch (tableName) {
			case "Institut":
				String name = request.getParameter("nameIns");
				System.out.println(name);

				Institut ins = new Institut(name);
				IInstitutDAO insDAO = factory.getInstitutDAO();
				insDAO.openSession();
				insDAO.save(ins);
				insDAO.closeSession();
				break;
			}
		} else if (request.getParameter("edit") != null) {
			switch (tableName) {
			case "Institut":
				int id = Integer.parseInt(request.getParameter("edit"));
				String name = request.getParameter("nameIns" + id);
				IInstitutDAO insDAO = factory.getInstitutDAO();
				insDAO.openSession();
				Institut ins = insDAO.getById(id);
				ins.setName(name);
				insDAO.update(ins);
				insDAO.closeSession();
				break;
			}
		}

		switch (tableName) {
		case "Institut":
			IInstitutDAO insDAO = factory.getInstitutDAO();
			insDAO.openSession();
			List<Institut> instituts = insDAO.getAll();
			insDAO.closeSession();
			request.setAttribute("lst", instituts);
			break;
		}

		request.getRequestDispatcher("edit" + tableName + ".jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
