package himu.lrs.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("name");
		String userPass = request.getParameter("pass");
		String userEmail = request.getParameter("email");
		String userMobile = request.getParameter("contact");

		RequestDispatcher dispatcher = null;
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/himu_database", "postgres",
					"isdb62");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO login_register_jsp(username, userpass, useremail, usermobile) VALUES(?, ?, ?, ?)");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPass);
			preparedStatement.setString(3, userEmail);
			preparedStatement.setString(4, userMobile);

			int rowCount = preparedStatement.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");

			if (rowCount > 0) {
				request.setAttribute("status", "success");

			} else {
				request.setAttribute("status", "failed");
			}

			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
