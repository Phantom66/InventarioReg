package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.con.DataBase;
import com.inventario.con.DataBaseException;
import com.inventario.dao.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {
	
	private DataBase conn = new DataBase();


	@Override
	public void insertar(Producto producto) throws DataBaseException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection()
					.prepareStatement("INSERT INTO producto(nombre, estatus, descripcion,id_persona)VALUES (?,?,?,?)");

			statement.setString(1, producto.getNombre());
			statement.setString(2, producto.getEstatus());
			statement.setString(3, producto.getDescripcion());
			statement.setString(4, producto.getPersona().getCedula());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);

		} finally {

			try {

				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}

		}

	}

	@Override
	public void salvar(Producto producto) throws DataBaseException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection().prepareStatement(
					"UPDATE producto SET nombre = ?, estatus = ?, descripcion = ? WHERE id_persona = ?");

			statement.setString(1, producto.getNombre());
			statement.setString(2, producto.getEstatus());
			statement.setString(3, producto.getDescripcion());
			statement.setString(4, producto.getPersona().getCedula());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);
			

		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}

		}

	}

	@Override
	public void borrar(Producto producto) throws DataBaseException {

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection().prepareStatement("DELETE FROM producto WHERE id = ?");
			statement.setInt(1, producto.getId());

			statement.execute();

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);

		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}

		}

	}

	@Override
	public List<Producto> buscarTodos() throws DataBaseException {

		List<Producto> producto = new ArrayList<Producto>();
		PreparedStatement statement = null;
		ResultSet filas = null;

		try {

			statement = this.conn.getConnection().prepareStatement("SELECT * FROM producto");
			filas = statement.executeQuery();

			while (filas.next()) {

				// Coloco cero el id, porque esto se generea automático.s
				Producto p = new Producto(filas.getString("nombre"), filas.getString("estatus"),
						filas.getString("descripcion"));

				producto.add(p);

			}

			return producto;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);

		} finally {

			try {

				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}

		}
	}

	@Override
	public Producto buscarPorClave(String id) throws DataBaseException {

		Producto p = null;
		PreparedStatement statement = null;
		ResultSet filas = null;

		try {

			statement = this.conn.getConnection().prepareStatement("SELECT * FROM producto WHERE id_persona = ?");

			statement.setString(1, id);
			filas = statement.executeQuery();

			if (filas.next()) {

				p = new Producto(filas.getString("nombre"), filas.getString("estatus"),
						filas.getString("descripcion"));

			} else {

				throw new DataBaseException(" Producto no se consigue " + id);
			}

			return p;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);

		} finally {

			try {

				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}

		}
	}

}
