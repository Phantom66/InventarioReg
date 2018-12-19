package com.inventario.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import com.inventario.bo.Perfil;
import com.inventario.con.JPAHelper;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl extends GenericDAOImpl<Perfil, String> implements PerfilDAO {

	@Override
	public List<Perfil> buscarTodos() {

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Perfil> perfil = (List<Perfil>) manager.createQuery("FROM Perfil perfiles");

		manager.close();

		return perfil;
	}

	@Override
	public Perfil buscarPorClave(String id) {

		EntityManagerFactory factoria = JPAHelper.getJPAFactory();
		EntityManager manager = factoria.createEntityManager();
		
		@SuppressWarnings("unchecked")
		TypedQuery<Perfil>consulta = (TypedQuery<Perfil>)manager.createQuery("SELECT perfil FROM Perfil perfil JOIN FECTH WHERE perfil.email=?1");
		consulta.setParameter(1, id);
		
		Perfil perfil = consulta.getSingleResult();
		
		return perfil;

	}

}
