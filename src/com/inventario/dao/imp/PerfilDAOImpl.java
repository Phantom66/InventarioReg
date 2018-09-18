package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.inventario.bo.Perfil;
import com.inventario.con.DataBase;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {
	
	private DataBase conn;
	
	public PerfilDAOImpl(){
		
		this.conn = new DataBase();
	}

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
			
		}finally{
			
			
			try {
				this.conn.getConnection().close();
				statement.close();
				
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
				
				int identificador =filas.getInt("id");
				String name = filas.getString("name");
				String email = filas.getString("email");
				String pass = filas.getString("password");
				
				
				perfil = new Perfil(identificador,name,email,pass);
				
			}else {
				
				return null;
			}
			
			
			return perfil;
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {

			try {
				this.conn.getConnection().close();
				statement.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
				
				
		
		return null;
	}

}
