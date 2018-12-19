package com.inventario.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import com.inventario.bo.Perfil;
import com.inventario.con.JPAHelper;
import com.inventario.dao.PerfilDAO;

public class PerfilDAOImpl extends GenericDAOImpl<Perfil, String> implements PerfilDAO {

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
