package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.inventario.bo.Perfil;
import com.inventario.con.DataBase;
import com.inventario.con.DataBaseException;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {

	private DataBase conn = new DataBase();
	PatternLayout layout = new PatternLayout("%m %n");
	ConsoleAppender consola = new ConsoleAppender(layout);
	
	private Logger log = Logger.getLogger("milog");
	

	@Override
	public void insertar(Perfil perfil){

		PreparedStatement statement = null;

		String sql = "INSERT INTO perfiles(name, email, password,id_persona) VALUES(?,?,?,?)";

		try {
			statement = this.conn.getConnection().prepareStatement(sql);
			statement.setString(1, perfil.getName());
			statement.setString(2, perfil.getEmail());
			statement.setString(3, perfil.getPassword());
			statement.setString(4, perfil.getPersona().getCedula());
			statement.executeUpdate();


		} catch (SQLException e) {// SQLException es del método preparedStatement
			
			log.error("Error en SQL en DAO " + e);
			throw new DataBaseException("Error en SQL en DAO " + e);

		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error del Statement " + e);

			}
		}

	}

	@Override
	public void salvar(Perfil perfil) {
		

	}

	@Override
	public void borrar(String perfil) {
		

	}

	@Override
	public List<Perfil> buscarTodos() {
		
		return null;
	}

	@Override
	public Perfil buscarPorClave(String id){

		PreparedStatement statement = null;
		ResultSet filas = null;
		Perfil perfil;
		try {
			statement = this.conn.getConnection().prepareStatement("SELECT * FROM perfiles WHERE email = ?");

			statement.setString(1, id);
			filas = statement.executeQuery();

			if (filas.next()) {

				perfil = new Perfil(filas.getString("name"), filas.getString("email"),
						filas.getString("password"));
				
				return perfil;

			} else {

				return null;
			}


		} catch (SQLException e) {

			throw new DataBaseException("Error en SQL en DAO " + e);

		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error del Statement " + e);

			}
		}

	}

}
