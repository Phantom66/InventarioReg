package com.inventario.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.dao.ProducotDAO;

public class ProductoDAOImpl implements ProducotDAO {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/Inventario";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	

	@Override
	public void insertar(Producto producto) {
		Connection conn = null;
		PreparedStatement mistatement = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("INSERT INTO producto(nombre, estatus, descripcion)VALUES (?,?,?)");
			
			mistatement.setString(1, producto.getNombre());
			mistatement.setString(2, producto.getEstatus());
			mistatement.setString(3, producto.getDescripcion());
			mistatement.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				conn.close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
	}

	@Override
	public void salvar(Producto producto) {
		
		Connection conn = null;
		PreparedStatement mistatement = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("UPDATE producto set nombre = ?, estatus = ?, descripcion = ? WHERE id = ?");
			
			mistatement.setString(1, producto.getNombre());
			mistatement.setString(2, producto.getEstatus());
			mistatement.setString(3, producto.getDescripcion());
			mistatement.setInt(4, producto.getIdentificador());
			
			mistatement.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}finally {
			
			try {
				conn.close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

	@Override
	public void borrar(Producto producto) {
		
		Connection conn = null ;
		PreparedStatement mistatement = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("DELETE FROM producto WHERE id = ?");
			
			mistatement.setInt(1, producto.getIdentificador());
			
			mistatement.execute();
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}finally {
			
			try {
				conn.close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
	}

	@Override
	public List<Producto> buscarTodos() {
		
		List<Producto>producto = new ArrayList<Producto>();
		Connection conn = null;
		PreparedStatement mistatement = null;
		ResultSet filas = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			mistatement = conn.prepareStatement("SELECT * FROM producto");
			
			filas = mistatement.executeQuery();
			
			while(filas.next()) {
				
				String nombre = filas.getString("nombre");
				String estatus = filas.getString("estatus");
				String descripcion = filas.getString("descripcion");
				
				Producto p = new Producto(nombre,estatus,descripcion);
				
				producto.add(p);
				
			}
			
			return producto;
			
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				conn.close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
		
		return null;
	}

	@Override
	public Producto buscarPorClave(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
