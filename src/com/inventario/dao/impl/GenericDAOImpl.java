package com.inventario.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.inventario.bo.Persona;
import com.inventario.con.JPAHelper;
import com.inventario.dao.GenericDAO;

/**
 * Mejorar esta clase y adpatarla a las reglas de negocio
 * @author lilibeth
 *
 * @param <T>
 * @param <Id>
 */

public abstract class GenericDAOImpl <T, Id extends Serializable> implements GenericDAO<T, Id> {
	
	
	private Class<T> claseDePersistencia;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		//Esto me permite obtener la clase que le pase
		this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public  void salvar(T t) {
		
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		
	
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(t);
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}
		
	}

	public  void borrar(T t) {
		
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(t));
			tx.commit();
			
		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	
	}
	
	
	@Override
	public List<T> buscarTodos() {

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();

		@SuppressWarnings("unchecked")
		TypedQuery<T> consulta = (TypedQuery<T>) manager.createQuery("FROM "+claseDePersistencia.getSimpleName()+"t");
		List<T> persona = consulta.getResultList();
		manager.close();
		// session.close();

		return persona;

	}

	
	public void insertar(T t) {
		
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;

		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.persist(t);
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}
		
		
	}

	
}
