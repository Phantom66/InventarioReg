package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.inventario.bo.Perfil;
import com.inventario.con.DataBase;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {
	
	private DataBase conn = new DataBase();
	

	@Override
	public void insertar(Perfil perfil) {
		
		PreparedStatement statement = null;
		
		String sql = "INSERT INTO perfiles(name, email, password) VALUES(?,?,?)";
		
		try {
			statement = this.conn.getConnection().prepareStatement(sql);
			statement.setString(1, perfil.getName());
			statement.setString(2, perfil.getEmail());
			statement.setString(3, perfil.getPassword());
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			
			try {
				statement.close();
				this.conn.closeConnection();
				
			} catch (SQLException e) {
				e.printStackTrace();
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
	public Perfil buscarPorClave(String id) {
		
		PreparedStatement statement = null;
		ResultSet filas = null;
		Perfil perfil = null;
		try {
			statement = this.conn.getConnection().prepareStatement("SELECT * FROM perfiles WHERE email = ?");
			
			statement.setString(1, id);
			filas = statement.executeQuery();
			
			if(filas.next()) {
		
				perfil = new Perfil(
						filas.getInt("id"),filas.getString("name"),filas.getString("email"),filas.getString("password")
						);
				
			}else {
				
				return null;
			}
			
			return perfil;
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}

}
