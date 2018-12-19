package com.inventario.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.inventario.bo.Producto;
import com.inventario.con.JPAHelper;
import com.inventario.dao.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public void insertar(Producto producto){
		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.persist(producto);
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}

	}

	@Override
	public void salvar(Producto producto){
		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(producto);
			tx.commit();
		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	
	}

	@Override
	public void borrar(Producto producto){
		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(producto));
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	

	}

	@Override
	public List<Producto> buscarTodos(){

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Producto> consulta= (TypedQuery<Producto>)manager.createQuery("FROM producto");

		List<Producto>producto = consulta.getResultList();

		return producto;

	}

	@Override
	public Producto buscarPorClave(String id){

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		TypedQuery<Producto> consulta = manager.createQuery("SELECT producto FROM Producto producto JOIN FETCH producto.persona WHERE producto.persona.cedula=?1", Producto.class);
		consulta.setParameter(1, id);
		
		Producto producto = consulta.getSingleResult();


		return producto;

	}

}
