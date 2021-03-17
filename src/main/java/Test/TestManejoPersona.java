package Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import entidad.Persona;

public class TestManejoPersona {

	public static void main(String[] args) {

		Connection conexion = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}

			PersonaDAO personaJdbc = new PersonaDaoJDBC(conexion);
			List<Persona> personas = new ArrayList<Persona>();
			
			personas = personaJdbc.select();

			for (Persona persona : personas) {
				System.out.println("Persona" + persona);
			}

			conexion.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
