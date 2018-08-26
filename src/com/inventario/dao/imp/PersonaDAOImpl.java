package com.inventario.dao.imp;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.inventario.bo.Persona;
import com.inventario.con.DataBase;

public class PersonaDAOImpl implements com.inventario.dao.PersonaDAO {
	
	private DataBase conn;
	
	public PersonaDAOImpl() {
		
		this.conn = new DataBase();
		
	}
	

	@Override
	public void insertar(Persona persona) {
		
		PreparedStatement mistatement = null;
		
		String sql = "INSERT INTO persona(cedula, nombre, apellido, telefono) VALUES  (?,?,?,?)";
		try {
			
			mistatement = this.conn.getConnection().prepareStatement(sql);
			
			mistatement.setInt(1, persona.getCedula());
			mistatement.setString(2, persona.getNombre());
			mistatement.setString(3, persona.getApellido());
			mistatement.setString(4, persona.getTelefono());

			mistatement.execute();
		
	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			
		}finally {
			
			try {
				this.conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
	}

	@Override
	public void salvar(Persona persona) {
		
		PreparedStatement mistatement = null;
		
		try {

			mistatement = this.conn.getConnection().prepareStatement("UPDATE persona set nombre = ?, apellido = ?, telefono = ? where cedula = ?");
			
			mistatement.setString(1, persona.getNombre());
			mistatement.setString(2, persona.getApellido());
			mistatement.setString(3, persona.getTelefono());
			mistatement.setInt(4, persona.getCedula());
			
			mistatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
			
		}finally {
			
			try {
				this.conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		}

	}

	@Override
	public void borrar(Persona persona) {
		
		PreparedStatement mistatement = null;
		
		try {

			mistatement = this.conn.getConnection().prepareStatement("DELETE FROM persona WHERE cedula = ?");
			
			mistatement.setInt(1, persona.getCedula());
			
			mistatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				this.conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Persona> buscarTodos() {
		
		List<Persona>persona = new ArrayList<Persona>();
		Statement mistatement = null;
		ResultSet filas = null;
		
		try {
			mistatement = this.conn.getConnection().createStatement();
			filas = mistatement.executeQuery("SELECT * FROM persona");
			
			while(filas.next()) {

				int cedula = filas.getInt("cedula");
				String nombre = filas.getString("nombre");
				String apellido = filas.getString("apellido");
				String telefono = filas.getString("telefono");
				
				Persona p = new Persona (cedula, telefono, nombre, apellido);
				
				persona.add(p);
				
			}
			
			return persona;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				this.conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

		
		
		return null;
	}

	@Override
	public Persona buscarPorClave(String id) {
		
		Persona persona = null;
		PreparedStatement mistatement = null;
		ResultSet filas = null;
		
		try {
			
			mistatement = this.conn.getConnection().prepareStatement("SELECT * FROM persona WHERE cedula = ?");
			mistatement.setString(1, id);
			filas = mistatement.executeQuery();
			
			if(filas.next()) {
				
				int cedula = filas.getInt("cedula");
				String telefono = filas.getString("telefono");
				String nombre = filas.getString("nombre");
				String apellido = filas.getString("apellido");
				
				persona = new Persona(cedula,telefono,nombre,apellido);
				
			}else {
				
				throw new Exception("Cedula no encontrada" + id);
			}
			
					
			return persona;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				this.conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		return null;
		
	}

}
