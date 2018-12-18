package com.inventario.dao.imp;

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

		//Hibernate
		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.save(producto);
		// session.getTransaction().commit();
		
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

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.saveOrUpdate(producto);
		// session.getTransaction().commit();
		
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

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.delete(producto);
		// session.getTransaction().commit();
		
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

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Producto> consulta= (TypedQuery<Producto>)manager.createQuery("FROM producto");

		List<Producto>producto = consulta.getResultList();
	
		// @SuppressWarnings("unchecked")
		// List<Producto> producto = session.createQuery("From Producto
		// producto").list();

		return producto;

	}

	@Override
	public Producto buscarPorClave(String id){

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		TypedQuery<Producto> consulta = manager.createQuery("SELECT producto FROM Producto producto JOIN FETCH producto.persona WHERE producto.persona.cedula=?1", Producto.class);
		consulta.setParameter(1, id);

		// @SuppressWarnings("unchecked")
		// TypedQuery<Producto> p = (TypedQuery<Producto>) consulta.getSingleResult();

		Producto producto = consulta.getSingleResult();


		return producto;

	}

}
