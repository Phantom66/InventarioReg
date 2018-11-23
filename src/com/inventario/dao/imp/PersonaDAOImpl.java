package com.inventario.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.con.DataBase;
import com.inventario.con.DataBaseException;
import com.inventario.con.HibernateHelper;


public class PersonaDAOImpl implements com.inventario.dao.PersonaDAO {

	private DataBase conn = new DataBase();


	@Override
	public void insertar(Persona persona) {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.save(persona);
		session.getTransaction().commit();

	}

	@Override
	public void salvar(Persona persona){

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.saveOrUpdate(persona);
		session.getTransaction().commit();
		
	}

	@Override
	public void borrar(Persona persona){
		
		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();

		session.delete(persona);
		session.getTransaction().commit();
		
	
	}

	@Override
	public List<Persona> buscarTodos(){

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		
		@SuppressWarnings("unchecked")
		List<Persona> persona = session.createQuery("From Persona persona").list();
		session.close();

		return persona;

	}

	@Override
	public Persona buscarPorClave(String cedula){

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		Persona persona = (Persona)session.get(Persona.class, cedula);
		
		return persona;

	}
	
	
	public Long getRows() {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();

		// Un manera de hacerlo
		Long numRows = (Long) session.createQuery("SELECT COUNT(persona) FROM Persona persona").uniqueResult();
		session.close();

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
