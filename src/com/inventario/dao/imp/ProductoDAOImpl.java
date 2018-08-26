package com.inventario.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.con.DataBase;
import com.inventario.dao.ProducotDAO;

public class ProductoDAOImpl implements ProducotDAO {
	
	
	
	private DataBase conn;

	public ProductoDAOImpl() {
		
		this.conn = new DataBase();
	}

	@Override
	public void insertar(Producto producto) {

		PreparedStatement mistatement = null;
		
		try {

			mistatement = conn.getConnection().prepareStatement("INSERT INTO producto(nombre, estatus, descripcion)VALUES (?,?,?)");
			
			mistatement.setString(1, producto.getNombre());
			mistatement.setString(2, producto.getEstatus());
			mistatement.setString(3, producto.getDescripcion());
			mistatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
	}

	@Override
	public void salvar(Producto producto) {

		PreparedStatement mistatement = null;
		
		try {
			
			mistatement = conn.getConnection().prepareStatement("UPDATE producto set nombre = ?, estatus = ?, descripcion = ? WHERE id = ?");
			
			mistatement.setString(1, producto.getNombre());
			mistatement.setString(2, producto.getEstatus());
			mistatement.setString(3, producto.getDescripcion());
			mistatement.setInt(4, producto.getIdentificador());
			
			mistatement.executeUpdate();
			
			
		}  catch (SQLException e) {

			e.printStackTrace();
			
		}finally {
			
			try {
				conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

	@Override
	public void borrar(Producto producto) {
		
		PreparedStatement mistatement = null;
		
		try {

			mistatement = conn.getConnection().prepareStatement("DELETE FROM producto WHERE id = ?");
			
			mistatement.setInt(1, producto.getIdentificador());
			
			mistatement.execute();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}finally {
			
			try {
				conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
	}

	@Override
	public List<Producto> buscarTodos() {
		
		List<Producto>producto = new ArrayList<Producto>();
		PreparedStatement mistatement = null;
		ResultSet filas = null;
		
		try {

			mistatement = conn.getConnection().prepareStatement("SELECT * FROM producto");
			
			filas = mistatement.executeQuery();
			
			while(filas.next()) {
				
				String nombre = filas.getString("nombre");
				String estatus = filas.getString("estatus");
				String descripcion = filas.getString("descripcion");
				
				Producto p = new Producto(nombre,estatus,descripcion);
				
				producto.add(p);
				
			}
			
			return producto;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
		
		return null;
	}

	@Override
	public Producto buscarPorClave(String id) {
		
		Producto p = null;
		PreparedStatement mistatement = null;
		ResultSet filas = null;
		
		try {

			mistatement = conn.getConnection().prepareStatement("SELECT * FROM producto WHERE id = ?");
			
			mistatement.setString(1, id);
			filas = mistatement.executeQuery();
			
			if(filas.next()) {
				
				int identificador = filas.getInt("id");
				String nombre = filas.getString("nombre");
				String estatus = filas.getString("estatus");
				String descripcion = filas.getString("descripcion");
				
				p = new Producto(identificador,nombre,estatus,descripcion);
				
			}else {
				
				throw new Exception(" Producto no se consigue " + id);
			}
			
			return p;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}finally {
			
			try {
				conn.getConnection().close();
				mistatement.close();
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
		}
		
		
		return null;
	}

	
}
