package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.inventario.bo.Perfil;
import com.inventario.con.DataBase;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {
	
	private DataBase conn;
	
	PerfilDAOImpl(){
		
		this.conn = new DataBase();
	}

	@Override
	public void insertar(Perfil perfil) {
		
		PreparedStatement statement = null;
		
		String sql = "Insert Into perfiles(name, email, password) Values(?,?,?)";
		
		try {
			statement = this.conn.getConnection().prepareStatement(sql);
			statement.setString(1, perfil.getName());
			statement.setString(2, perfil.getEmail());
			statement.setString(3, perfil.getPassword());
			statement.executeQuery();
			
			
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
		
		return null;
	}

}
