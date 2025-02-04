package himu.sms.student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	@Override
	public void init() {
		studentDAO = new StudentDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("address");

			Student student = new Student(name, email, mobile, address);

			try {
				studentDAO.addStudent(student);
				response.sendRedirect("list_students.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "error adding student: " + e.getMessage());
				request.getRequestDispatcher("add_student.jsp").forward(request, response);
			}
		}
	}
}
