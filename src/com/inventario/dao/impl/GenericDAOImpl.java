package com.inventario.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
	
	
	public T buscarPorClave(Id id) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		T objeto = null;
		try {
			objeto = (T) manager.find(claseDePersistencia, id);
			return objeto;
		} finally {
			manager.close();
		}
	}

}
