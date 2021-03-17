package datos;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "admin";

	public static DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(JDBC_URL);
		ds.setUsername(JDBC_USER);
		ds.setPassword(JDBC_PASSWORD);

		// definimos el tamaño inicial del pool de conexiones
		ds.setInitialSize(5);
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
				
	}

	public static void close(ResultSet resultado) throws SQLException {
		resultado.close();
	}

	public static void close(Statement sentencia) throws SQLException {
		sentencia.close();
	}

	public static void close(PreparedStatement sentencia) throws SQLException {
		sentencia.close();
	}

	public static void close(Connection conexion) throws SQLException {
		conexion.close();
	}
}
