package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	private final String CONNECTION = "jdbc:mysql://127.0.0.1:3306/cinema_ticket_sale_pj?characterEncoding=latin1&useConfigs=maxPerformance";
	private final String PASSWORD = "mdcr@2022";
	private static Connection con = null;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {

		if (null == con) {
			con = (Connection) DriverManager.getConnection(this.CONNECTION, "root", this.PASSWORD);
		}
		return con;
	}
}
