package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import util.ShowTable;

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
		ShowTable.show(request, tableName, factory);

		request.getRequestDispatcher("edit" + tableName + ".jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
