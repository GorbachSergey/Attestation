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
import daoInterfaces.IMarkDAO;
import daoInterfaces.IStudentDAO;
import daoInterfaces.ISubjectDAO;
import domain.Mark;
import domain.Student;
import domain.Subject;

@WebServlet("/AddMarkServlet")
public class AddMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddMarkServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DAOFactory factory = (DAOFactory) session.getAttribute("factory");
		int subjectId = (int) session.getAttribute("subjectId");
		IMarkDAO markDAO = factory.getMarkDAO();
		IStudentDAO studentDAO = factory.getStudentDAO();
		markDAO.openSession();
		String clear = request.getParameter("clear");
		if (clear != null) {
			if (!clear.equals("")) {
				int markId = Integer.parseInt(clear);
				Mark m = markDAO.getById(markId);
				if (m != null)
					markDAO.delete(m);
			}
		} else {
			int studentId = Integer.parseInt(request.getParameter("studId"));
			int mark = Integer.parseInt(request.getParameter("mark" + studentId));
			int pass = Integer.parseInt(request.getParameter("pass" + studentId));
			Mark m = markDAO.getMarkBySubjectAndStudent(subjectId, studentId);
			if (m == null) {
				m = new Mark();
				m.setMark(mark);
				m.setCountOfPass(pass);
				studentDAO.openSession();
				Student s = studentDAO.getById(studentId);
				studentDAO.closeSession();
				m.setStudent(s);
				ISubjectDAO subjectDAO = factory.getSubjectDAO();
				subjectDAO.openSession();
				Subject sub = subjectDAO.getById(subjectId);
				subjectDAO.closeSession();
				m.setSubject(sub);
				markDAO.save(m);
			} else {
				m.setMark(mark);
				m.setCountOfPass(pass);
				markDAO.update(m);
			}
		}
		SortedMap<Student, Mark> map = new TreeMap<>();
		int groupId = (int) session.getAttribute("groupId");
		studentDAO.openSession();
		List<Student> students = studentDAO.getStudentsByGroupId(groupId);
		studentDAO.closeSession();
		for (Student s : students) {
			Mark mar = markDAO.getMarkBySubjectAndStudent(subjectId, s.getId());
			map.put(s, mar);
		}
		markDAO.closeSession();
		request.setAttribute("list", map);
		request.getRequestDispatcher("showStudents.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
