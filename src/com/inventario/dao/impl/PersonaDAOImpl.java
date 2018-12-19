package com.inventario.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.con.JPAHelper;
import com.inventario.dao.PersonaDAO;


public class PersonaDAOImpl implements PersonaDAO {

	@Override
	public void insertar(Persona persona) {
		
		//JPA
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.persist(persona);
			tx.commit();
			
		}catch(PersistenceException e) {
			
			manager.getTransaction().rollback();
			throw e;
			
		}finally {
			
			manager.close();
		}

		
		
		
		

	}

	@Override
	public void salvar(Persona persona) {
		
		//JPA
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		
		System.out.println(" ---" + persona.getProductos());
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(persona);
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	

	}

	@Override
	public void borrar(Persona persona) {
		
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(persona));
			tx.commit();
			
		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	

	}

	@Override
	public List<Persona> buscarTodos() {

		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		

		@SuppressWarnings("unchecked")
		TypedQuery<Persona> consulta = (TypedQuery<Persona>) manager.createQuery("From Persona");
		List<Persona>persona = consulta.getResultList();
		manager.close();
		//session.close();

		return persona;

	}

	@Override
	public Persona buscarPorClave(String cedula) {
		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Persona> consulta = (TypedQuery<Persona>)manager.createQuery("SELECT p FROM Persona p WHERE p.cedula=?1");
		consulta.setParameter(1,cedula);
		
		Persona persona =consulta.getSingleResult(); 

		return persona;

	}

	/**
	 * Método para devolver las filas de la tabla persona.
	 * 
	 * @return
	 */
	public Long getRows() {

	
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();

		// Un manera de hacerlo
		Long numRows = (Long) manager.createQuery("SELECT COUNT(producto) FROM Producto producto").getSingleResult();
		
		manager.close();

		return numRows;
	}

	public List<Producto> getPerPagination(int pagActual, int perReg) {

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

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		//Objeto Query(Hibernate) y Objeto Query(JPA) pendiente 
		Query q = manager.createQuery("SELECT producto From Producto producto JOIN FETCH producto.persona");
		q.setFirstResult(start);
		q.setMaxResults(perReg);

		@SuppressWarnings("unchecked")
		List<Producto> produc = q.getResultList();

		return produc;

	}
	
	
}
