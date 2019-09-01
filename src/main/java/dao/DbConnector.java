package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnector {
	public Connection getConnection() throws DAOException {
		Context context;

		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/mywebapp");
			return dataSource.getConnection();

		} catch (SQLException | NamingException e) {
			throw new DAOException("Dao exception was generated", e);
		}
	}
}
