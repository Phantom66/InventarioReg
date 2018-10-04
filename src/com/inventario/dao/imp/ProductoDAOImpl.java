package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.con.DataBase;
import com.inventario.dao.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {
	
	private DataBase conn = new DataBase();


	@Override
	public void insertar(Producto producto, int id) throws SQLException, ClassNotFoundException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection()
					.prepareStatement("INSERT INTO producto(nombre, estatus, descripcion,id_persona)VALUES (?,?,?,?)");

			statement.setString(1, producto.getNombre());
			statement.setString(2, producto.getEstatus());
			statement.setString(3, producto.getDescripcion());
			statement.setInt(4, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw e;

		} catch (ClassNotFoundException e) {
			
			System.out.println("Error de SQL" + e.getMessage());
			throw e;
			
		} finally {

			try {

				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public void salvar(Producto producto) throws SQLException, ClassNotFoundException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection().prepareStatement(
					"UPDATE producto SET nombre = ?, estatus = ?, descripcion = ? WHERE id_persona = ?");

			statement.setString(1, producto.getNombre());
			statement.setString(2, producto.getEstatus());
			statement.setString(3, producto.getDescripcion());
			statement.setInt(4, producto.getPersona().getCedula());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw e;
			

		} catch (ClassNotFoundException e) {
			
			System.out.println("Error de SQL" + e.getMessage());
			throw e;
			
		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public void borrar(Producto producto) throws SQLException, ClassNotFoundException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection().prepareStatement("DELETE FROM producto WHERE id = ?");
			statement.setInt(1, producto.getId());

			statement.execute();

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw e;

		} catch (ClassNotFoundException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw e;
			
		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Producto> buscarTodos() throws SQLException, ClassNotFoundException {

		List<Producto> producto = new ArrayList<Producto>();
		PreparedStatement statement = null;
		ResultSet filas = null;

		try {

			statement = this.conn.getConnection().prepareStatement("SELECT * FROM producto");
			filas = statement.executeQuery();

			while (filas.next()) {

				// Coloco cero el id, porque esto se generea automático.s
				Producto p = new Producto(0, filas.getString("nombre"), filas.getString("estatus"),
						filas.getString("descripcion"));

				producto.add(p);

			}

			return producto;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw e;

		} catch (ClassNotFoundException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw e;
			
		} finally {

			try {

				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	@Override
	public Producto buscarPorClave(String id) throws SQLException, ClassNotFoundException {

		Producto p = null;
		PreparedStatement statement = null;
		ResultSet filas = null;

		try {

			statement = this.conn.getConnection().prepareStatement("SELECT * FROM producto WHERE id_persona = ?");

			statement.setString(1, id);
			filas = statement.executeQuery();

			if (filas.next()) {

				p = new Producto(filas.getInt("id"), filas.getString("nombre"), filas.getString("estatus"),
						filas.getString("descripcion"));

			} else {

				throw new Exception(" Producto no se consigue " + id);
			}

			return p;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw e;

		} catch (ClassNotFoundException e) {
			System.out.println("Error de SQL" + e.getMessage());
			throw e;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

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
