package com.inventario.dao.imp;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.inventario.bo.Perfil;
import com.inventario.con.HibernateHelper;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl implements PerfilDAO {

	@Override
	public void insertar(Perfil perfil) {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.save(perfil);
		session.getTransaction().commit();

	}

	@Override
	public void salvar(Perfil perfil) {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.saveOrUpdate(perfil);
		session.getTransaction().commit();

	}

	@Override
	public void borrar(Perfil perfil) {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.delete(perfil);
		session.getTransaction().commit();

	}

	@Override
	public List<Perfil> buscarTodos() {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		@SuppressWarnings("unchecked")
		List<Perfil> perfil = session.createQuery("From Perfil perfiles").list();

		return perfil;
	}

	@Override
	public Perfil buscarPorClave(String id) {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();

		Perfil perfil = session.get(Perfil.class, "email");
		return perfil;

	}

}
