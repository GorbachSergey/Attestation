package util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DAOFactory;
import daoInterfaces.IFacultyDAO;
import daoInterfaces.IInstitutDAO;
import daoInterfaces.ISpecialtyDAO;
import domain.Faculty;
import domain.Institut;
import domain.Specialty;

public class ShowTable {

	public static void show(HttpServletRequest request, String tableName, DAOFactory factory) {
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
		case "Specialty":
			int idS = Integer.parseInt(request.getParameter("id"));
			ISpecialtyDAO specDAO = factory.getSpecialtyDAO();
			specDAO.openSession();
			Specialty s = specDAO.getById(idS);
			specDAO.closeSession();
			Faculty fa = s.getFaculty();
			List<Specialty> specialties = fa.getSpecialties();
			IFacultyDAO facDAO = factory.getFacultyDAO();
			facDAO.openSession();
			List<Faculty> fac = facDAO.getFacultiesByInstitutId(fa.getInstitut().getId());
			facDAO.closeSession();
			request.setAttribute("lst", specialties);
			request.setAttribute("lst1", fac);
			break;
		}
	}
}
