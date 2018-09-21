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

			// mistatement =
			// this.conn.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			mistatement = this.conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			mistatement.setInt(1, persona.getCedula());
			mistatement.setString(2, persona.getNombre());
			mistatement.setString(3, persona.getApellido());
			mistatement.setString(4, persona.getTelefono());

			mistatement.executeUpdate();

			// Una manera para obtener el id del Insert
			// ResultSet resul = mistatement.getGeneratedKeys();
			// int id = 0;
			// if (resul.next()) {
			//
			// id = resul.getInt(1);
			// }

			// return persona.getCedula();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {
				this.conn.getConnection().close();
				mistatement.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		// return 0;
	}

	@Override
	public void salvar(Persona persona) {

		PreparedStatement mistatement = null;

		try {

			mistatement = this.conn.getConnection()
					.prepareStatement("UPDATE persona set nombre = ?, apellido = ?, telefono = ? where cedula = ?");

			mistatement.setString(1, persona.getNombre());
			mistatement.setString(2, persona.getApellido());
			mistatement.setString(3, persona.getTelefono());
			mistatement.setInt(4, persona.getCedula());

			mistatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {
				this.conn.getConnection().close();
				mistatement.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void borrar(String cedula) {

		PreparedStatement mistatement = null;

		try {

			mistatement = this.conn.getConnection().prepareStatement("DELETE FROM persona WHERE cedula = ?");

			mistatement.setString(1, cedula);

			mistatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

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

		List<Persona> persona = new ArrayList<Persona>();
		Statement mistatement = null;
		ResultSet filas = null;

		try {
			mistatement = this.conn.getConnection().createStatement();
			filas = mistatement.executeQuery("SELECT * FROM persona");

			while (filas.next()) {

				int id = filas.getInt("id");
				int cedula = filas.getInt("cedula");
				String nombre = filas.getString("nombre");
				String apellido = filas.getString("apellido");
				String telefono = filas.getString("telefono");

				Persona p = new Persona(cedula, nombre, apellido, telefono);
				//Lo hago de esta manera para realizar prueba, debo optimizar.
				p.setId(id);
				persona.add(p);

			}

			return persona;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

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

			if (filas.next()) {

				int cedula = filas.getInt("cedula");
				String telefono = filas.getString("telefono");
				String nombre = filas.getString("nombre");
				String apellido = filas.getString("apellido");

				persona = new Persona(cedula, nombre, apellido, telefono);

			} else {

				throw new Exception("Cedula no encontrada" + id);
			}

			return persona;
			
			

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				this.conn.getConnection().close();
				mistatement.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return null;

	}
	
	
	public int getRows() {

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

			e.printStackTrace();

		} finally {
			try {
				this.conn.getConnection().close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return numRows;
	}

	
	public List<Persona>getPerPagination(int pagActual, int perReg){
		
		PreparedStatement statement = null;
		ResultSet filas = null;
		List<Persona>p = new ArrayList<Persona>();
		Persona persona;
		
		/*
		 * Con este cálculo puedo ir  corriendo las posiciones del registro
		 * de mi tabla.
		 * 
		 * Ejemplo: Si quiero que me muestre de 5 en 5 los registro de mi tabla
		 * , y quiero que me muestre un primer reglón, realizamos el cálculo:
		 * 
		 * pagActual = 1;
		 * pagPerReg = 5;
		 * start = ?;
		 * 
		 * start = (1*5)-5 = 0;
		 * start = 0;
		 * 
		 * Con esto le indico a mi sentencia Sql(SELECT * FROM `persona` LIMIT 0 ,5)
		 * que me muestre los cinco primeros registros de la tabla, al realiza el cálculo
		 * nuevamente, cambiamos la página a 2:
		 * 
		 * pagActual = 2;
		 * pagPerReg = 5;
		 * start = ?;
		 * 
		 * start = (2*5)-5 = 5;
		 * start = 5;
		 * 
		 * Con esto le indicamos a la sentencia Sql Sql(SELECT * FROM `persona` LIMIT 5 ,5)
		 * que me muestre los tres segundo registro.
		 * 
		 * Esto sucesivamente a hasta mostra el final de los registro.
		 * 
		 * Cabe mencionar que la clave está como la palabra LIMIT en la sentencia SQL
		 * muestra este tipo de información, el muestra la cantidad de valores
		 * de acuerdo le indiquemos en el segundo parámetro.
		 * 
		 * si es de 3 en 3 o de 10 en 10, y va desde la posición de incio 0 hasta la posición
		 * del registro que le indiquemos, ejemplo: 
		 * posición 0(primeros registros a mostrar) y de acuerdo al segundo parámetro se empieza,
		 * a contar, en caso de que sea 5 el segundo parámetro; 0,1,2,3,4, como si fuera una array luego se debe mover
		 * a la posición 5; 5,6,7,8,9, y de esta manera mostraría los primeros 10 registro de 
		 * 5 en 5 de forma consecutiva.
		 * 
		 */
		 int start = (pagActual * perReg) - perReg;
		
		try {
			statement = this.conn.getConnection().prepareStatement("SELECT * FROM persona LIMIT ?,?");
			
			statement.setInt(1, start);
			statement.setInt(2, perReg);
			
			filas = statement.executeQuery();
			
			
			while(filas.next()) {
				
				int id = filas.getInt("id");
				int cedula = filas.getInt("cedula");
				String nombre = filas.getString("nombre");
				String apellido = filas.getString("apellido");
				String telefono = filas.getString("telefono");
				
				persona = new Persona(cedula,nombre,apellido,telefono);
				persona.setId(id);
				
				p.add(persona);
				
			}
			
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
		
		return p;
	}
}
