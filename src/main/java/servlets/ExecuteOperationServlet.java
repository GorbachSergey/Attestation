package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import daoInterfaces.IFacultyDAO;
import daoInterfaces.IInstitutDAO;
import daoInterfaces.ISpecialtyDAO;
import domain.Faculty;
import domain.Institut;
import domain.Specialty;
import util.ShowTable;

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
			}
		}
		ShowTable.show(request, tableName, factory);

		request.getRequestDispatcher("private/edit" + tableName + ".jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
