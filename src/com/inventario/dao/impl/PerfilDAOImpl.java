package com.inventario.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.inventario.bo.Perfil;
import com.inventario.con.JPAHelper;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {

	@Override
	public void insertar(Perfil perfil) {

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.save(perfil);
		// session.getTransaction().commit();
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;

		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.persist(perfil);
			tx.commit();

		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}

	}

	@Override
	public void salvar(Perfil perfil) {

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.saveOrUpdate(perfil);
		// session.getTransaction().commit();

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;
	
		try {
			tx = manager.getTransaction();
			manager.merge(perfil);
			tx.commit();
		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}

	}

	@Override
	public void borrar(Perfil perfil) {

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// session.beginTransaction();
		// session.delete(perfil);
		// session.getTransaction().commit();

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(perfil));
			tx.commit();
		} catch (PersistenceException e) {

			manager.getTransaction().rollback();
			throw e;

		} finally {

			manager.close();
		}	
	}

	@Override
	public List<Perfil> buscarTodos() {

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// @SuppressWarnings("unchecked")
		// List<Perfil> perfil = session.createQuery("From Perfil perfiles").list();

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Perfil> perfil = (List<Perfil>) manager.createQuery("FROM Perfil perfiles");

		manager.close();

		return perfil;
	}

	@Override
	public Perfil buscarPorClave(String id) {

		// SessionFactory factoria = HibernateHelper.getSessionFactory();
		// Session session = factoria.openSession();
		// Perfil perfil = session.get(Perfil.class, "email");
		
		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Perfil>consulta = (TypedQuery<Perfil>)manager.createQuery("SELECT perfil FROM Perfil perfil JOIN FECTH WHERE perfil.email=?1");
		consulta.setParameter(1, id);
		
		Perfil perfil = consulta.getSingleResult();
		
		return perfil;

	}

}
