package himu.lrs.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String userPass = request.getParameter("password");

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/himu_database",
					"postgres", "isdb62");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM login_register_jsp WHERE username = ? AND userpass = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPass);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				session.setAttribute("name", resultSet.getString("username"));
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
