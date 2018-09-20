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
		
		//Verificar esta lógica.
		 int start = (pagActual * perReg) - perReg;
//
//		System.out.println(start);
		
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
