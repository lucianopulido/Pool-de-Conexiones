package datos;

import java.sql.SQLException;
import java.util.List;

import entidad.Persona;

public interface PersonaDAO {

	public List<Persona> select()throws SQLException;

	public int insert(Persona person)throws SQLException;

	public int update(Persona person)throws SQLException;

	public int delete(Persona person) throws SQLException;

}
