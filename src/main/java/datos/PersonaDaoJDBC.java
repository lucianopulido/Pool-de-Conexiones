package datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidad.Persona;

public class PersonaDaoJDBC implements PersonaDAO {

	private Connection conexionTransaccional;

	private static final String SQL_SELECT = "SELECT idPersona, nombre, apellido, email, telefono FROM persona";
	private static final String SQL_INSERT = "INSERT INTO persona(nombre,apellido,email,telefono) VALUES(?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ? , email = ? , telefono = ? WHERE idPersona = ? ";
	private static final String SQL_DELETE = "DELETE FROM persona WHERE idPersona = ?";

	public PersonaDaoJDBC() {
	}

	public PersonaDaoJDBC(Connection conexionTransaccional) {
		this.conexionTransaccional = conexionTransaccional;
	}

	public List<Persona> select() throws SQLException {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		Persona persona;
		List<Persona> personas = new ArrayList<Persona>();

		try {
			conexion = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			sentencia = conexion.prepareStatement(SQL_SELECT);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				int idPersona = resultado.getInt("idPersona");
				String nombre = resultado.getString("nombre");
				String apellido = resultado.getString("apellido");
				String email = resultado.getString("email");
				int telefono = resultado.getInt("telefono");

				persona = new Persona(idPersona, nombre, apellido, email, telefono);
				personas.add(persona);
			}
		} finally {
			try {
				Conexion.close(resultado);
				Conexion.close(sentencia);
				if (this.conexionTransaccional == null) {
					Conexion.close(conexion);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return personas;

	}

	public int insert(Persona persona) throws SQLException {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		int registros = 0;

		try {

			conexion = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			sentencia = conexion.prepareStatement(SQL_INSERT);
			sentencia.setString(1, persona.getNombre());
			sentencia.setString(2, persona.getApellido());
			sentencia.setString(3, persona.getEmail());
			sentencia.setInt(4, persona.getTelefono());
			registros = sentencia.executeUpdate();

		}  finally {
			try {
				Conexion.close(sentencia);
				if (this.conexionTransaccional == null) {
					Conexion.close(conexion);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return registros;
	}

	public int update(Persona persona) throws SQLException {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		int registros = 0;

		try {

			conexion = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			sentencia = conexion.prepareStatement(SQL_UPDATE);
			sentencia.setString(1, persona.getNombre());
			sentencia.setString(2, persona.getApellido());
			sentencia.setString(3, persona.getEmail());
			sentencia.setInt(4, persona.getTelefono());
			sentencia.setInt(5, persona.getIdPersona());
			registros = sentencia.executeUpdate();

		}  finally {
			try {
				Conexion.close(sentencia);
				if (this.conexionTransaccional == null) {
					Conexion.close(conexion);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return registros;
	}

	public int delete(Persona persona) throws SQLException {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		int registros = 0;

		try {

			conexion = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
			sentencia = conexion.prepareStatement(SQL_DELETE);
			sentencia.setInt(1, persona.getIdPersona());
			registros = sentencia.executeUpdate();

		}  finally {
			try {
				Conexion.close(sentencia);
				if (this.conexionTransaccional == null) {
					Conexion.close(conexion);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return registros;
	}

}
