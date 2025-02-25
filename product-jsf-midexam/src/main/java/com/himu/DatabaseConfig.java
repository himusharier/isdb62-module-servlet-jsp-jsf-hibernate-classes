package com.himu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
	static final String URL = "jdbc:postgresql://localhost:5432/himu_database";
	static final String USERNAME = "postgres";
	static final String PASSWORD = "isdb62";

	public static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
