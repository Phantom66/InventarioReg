package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.con.DataBase;
import com.inventario.con.DataBaseException;


public class PersonaDAOImpl implements com.inventario.dao.PersonaDAO {

	private DataBase conn = new DataBase();


	@Override
	public void insertar(Persona persona){

		PreparedStatement statement = null;

		String sql = "INSERT INTO persona(cedula, nombre, apellido, telefono) VALUES  (?,?,?,?)";
		try {

			// mistatement =
			// this.conn.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// Una manera para obtener el id del Insert
			// ResultSet resul = mistatement.getGeneratedKeys();
			// int id = 0;
			// if (resul.next()) {
			//
			// id = resul.getInt(1);
			// }

			// return persona.getCedula();
			
			statement = this.conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, persona.getCedula());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getApellido());
			statement.setString(4, persona.getTelefono());
			statement.executeUpdate();


		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error SQL PreparedStatement ", e);

		} finally {

			try {
				
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error Close PreparedStatement ", e);
			}
		}

	}

	@Override
	public void salvar(Persona persona){

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection()
					.prepareStatement("UPDATE persona SET nombre = ?, apellido = ?, telefono = ? WHERE cedula = ?");

			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCedula());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error SQL PreparedStatement ", e);

		} finally {

			try {
				
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error Close PreparedStatement ", e);
			}
		}

	}

	@Override
	public void borrar(String cedula){

		PreparedStatement statement = null;

		try {

			statement = this.conn.getConnection().prepareStatement("DELETE FROM persona WHERE cedula = ?");
			statement.setString(1, cedula);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error SQL PreparedStatement ", e);

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
	public List<Persona> buscarTodos(){

		List<Persona> persona = new ArrayList<Persona>();
		Statement statement = null;
		ResultSet filas = null;

		try {
			statement = this.conn.getConnection().createStatement();
			filas = statement.executeQuery("SELECT * FROM persona");

			while (filas.next()) {

				Persona p = new Persona(

						filas.getString("cedula"), filas.getString("nombre"), filas.getString("apellido"),
						filas.getString("telefono")

				);
				// Lo hago de esta manera para realizar prueba, debo optimizar.
				//p.setId(filas.getInt("id"));
				persona.add(p);

			}

			return persona;

		} catch (SQLException e) {
			System.out.println("Clase no encontrada " + e.getMessage());
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
	public Persona buscarPorClave(String id){

		Persona persona = null;
		PreparedStatement mistatement = null;
		ResultSet filas = null;

		try {

			mistatement = this.conn.getConnection().prepareStatement("SELECT * FROM persona WHERE cedula = ?");
			mistatement.setString(1, id);
			filas = mistatement.executeQuery();

			if (filas.next()) {

				persona = new Persona(filas.getString("cedula"), filas.getString("nombre"), filas.getString("apellido"),
						filas.getString("telefono"));

			} else {

				throw new DataBaseException("Cedula no encontrada " + id);
			}

			return persona;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error PreparedStatement ", e);

		} finally {

			try {
				this.conn.closeConnection();
				mistatement.close();

			} catch (SQLException e) {

				throw new DataBaseException("Error close PreparedStatement ", e);
			}
		}

	}
	
	
	public int getRows(){

		int numRows = 0;

		Statement statement = null;
		ResultSet filas = null;

		try {
			statement = this.conn.getConnection().createStatement();
			filas = statement.executeQuery("SELECT * FROM persona");

			while (filas.next()) {

				numRows++;
			}

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error Statement ", e);

		} finally {
			
			try {
				
				statement.close();
				this.conn.closeConnection();
				
				
			} catch (SQLException e) {
	
				throw new DataBaseException("Error close Statement ", e);
			}
		}

		return numRows;
	}

	
	public List<Producto> getPerPagination(int pagActual, int perReg){

		PreparedStatement statement = null;
		ResultSet filas = null;
		List<Producto> p = new ArrayList<Producto>();
		Producto producto;
		Persona persona = new Persona();

		/*
		 * Con este cálculo puedo ir corriendo las posiciones del registro de mi tabla.
		 * 
		 * Ejemplo: Si quiero que me muestre de 5 en 5 los registro de mi tabla , y
		 * quiero que me muestre un primer reglón, realizamos el cálculo:
		 * 
		 * pagActual = 1; pagPerReg = 5; start = ?;
		 * 
		 * start = (1*5)-5 = 0; start = 0;
		 * 
		 * Con esto le indico a mi sentencia Sql(SELECT * FROM `persona` LIMIT 0 ,5) que
		 * me muestre los cinco primeros registros de la tabla, al realiza el cálculo
		 * nuevamente, cambiamos la página a 2:
		 * 
		 * pagActual = 2; pagPerReg = 5; start = ?;
		 * 
		 * start = (2*5)-5 = 5; start = 5;
		 * 
		 * Con esto le indicamos a la sentencia Sql Sql(SELECT * FROM `persona` LIMIT 5
		 * ,5) que me muestre los cinco segundo registro.
		 * 
		 * Esto sucesivamente a hasta mostra el final de los registro.
		 * 
		 * Cabe mencionar que la clave está como la palabra LIMIT en la sentencia SQL
		 * muestra este tipo de información, el muestra la cantidad de valores de
		 * acuerdo le indiquemos en el segundo parámetro.
		 * 
		 * si es de 3 en 3 o de 10 en 10, y va desde la posición de incio 0 hasta la
		 * posición del registro que le indiquemos, ejemplo: posición 0(primeros
		 * registros a mostrar) y de acuerdo al segundo parámetro se empieza, a contar,
		 * en caso de que sea 5 el segundo parámetro; 0,1,2,3,4, como si fuera una array
		 * luego se debe mover a la posición 5; 5,6,7,8,9, y de esta manera mostraría
		 * los primeros 10 registro de 5 en 5 de forma consecutiva.
		 * 
		 */
		int start = (pagActual * perReg) - perReg;

		try {
			
			statement = this.conn.getConnection().prepareStatement("SELECT producto.id, producto.nombre, producto.estatus, producto.descripcion, persona.nombre as pnombre, persona.cedula, producto.id_persona FROM producto, persona WHERE producto.id_persona = persona.cedula LIMIT ?,?");
			statement.setInt(1, start);
			statement.setInt(2, perReg);

			filas = statement.executeQuery();

			while (filas.next()) {
				
				producto = new Producto(
							filas.getString("nombre"), 
							filas.getString("estatus"), filas.getString("descripcion"), 
							new Persona(filas.getString("cedula"), filas.getString("pnombre"), "", "")
							);
				//Mejorar esta locura Producto
				producto.setIdPersona(filas.getString("id_persona"));
				producto.setId(filas.getInt("id"));
				
				System.out.println("--P-- "+producto.toString());
				
				p.add(producto);

			}

			return p;

		} catch (SQLException e) {

			System.out.println("Clase no encontrada" + e.getMessage());
			throw new DataBaseException("Error Statement ", e);

		} finally {

			try {
				statement.close();
				this.conn.closeConnection();

			} catch (SQLException e) {

				throw new DataBaseException("Error close Statement ", e);
			}

		}

	}
	
	
	
}
