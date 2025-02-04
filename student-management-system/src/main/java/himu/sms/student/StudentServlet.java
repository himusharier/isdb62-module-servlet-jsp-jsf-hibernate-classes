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
				String successMessage = "student record added successfully!";
				request.setAttribute("successMessage", successMessage);
				// request.getRequestDispatcher("../pages/add_student.jsp").forward(request,
				// response);
				response.sendRedirect("../pages/list-students.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				String errorMessage = "error adding student: " + e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("../pages/add-student.jsp").forward(request, response);
			}
		}
	}
}
