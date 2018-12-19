package com.inventario.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import com.inventario.bo.Producto;
import com.inventario.con.JPAHelper;
import com.inventario.dao.ProductoDAO;

public class ProductoDAOImpl extends GenericDAOImpl<Producto, String> implements ProductoDAO {

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
