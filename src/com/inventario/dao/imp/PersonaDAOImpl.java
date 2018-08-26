package com.inventario.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;

public class PersonaDAOImpl implements com.inventario.dao.PersonaDAO {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/Inventario";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	@Override
	public void insertar(Persona persona) {
		
		Connection conn = null;
		PreparedStatement mistatement = null;
		
		String sql = "INSERT INTO persona(cedula, nombre, apellido, telefono) VALUES  (?,?,?,?)";
		try {
			
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement(sql);
			
			mistatement.setInt(1, persona.getCedula());
			mistatement.setString(2, persona.getNombre());
			mistatement.setString(3, persona.getApellido());
			mistatement.setString(4, persona.getTelefono());

			
			mistatement.execute();
		
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			
		}finally {
			
			try {
			conn.close();
			mistatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		/*
			for(Producto p: persona.getProducto()) {
				
				System.out.println("Insertando Persona "+ persona.getNombre()+" producto asignados "+ p.getNombre());
				
				
			}
			*/
	}

	@Override
	public void salvar(Persona persona) {
		Connection conn = null;
		PreparedStatement mistatement = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("UPDATE persona set nombre = ?, apellido = ?, telefono = ? where cedula = ?");
			
			mistatement.setString(1, persona.getNombre());
			mistatement.setString(2, persona.getApellido());
			mistatement.setString(3, persona.getTelefono());
			mistatement.setInt(4, persona.getCedula());
			
			mistatement.executeUpdate();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
			conn.close();
			mistatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

	@Override
	public void borrar(Persona persona) {
		
		Connection conn = null;
		PreparedStatement mistatement = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("DELETE FROM persona WHERE cedula = ?");
			
			mistatement.setInt(1, persona.getCedula());
			
			mistatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
			conn.close();
			mistatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

	@Override
	public List<Persona> buscarTodos() {
		
		List<Persona>persona = new ArrayList<Persona>();
		Connection conn = null;
		Statement mistatement = null;
		ResultSet filas = null;
		
		try {
			Class.forName(DRIVER);
			conn= DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.createStatement();
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
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				conn.close();
				mistatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		return null;
	}

	@Override
	public Persona buscarPorClave(String id) {
		
		Persona persona = null;
		Connection conn = null;
		PreparedStatement mistatement = null;
		ResultSet filas = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("SELECT * FROM persona WHERE cedula = ?");
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
			conn.close();
			mistatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return null;
		
	}

}
