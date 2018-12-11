package com.inventario.dao.imp;

import com.inventario.dao.DAOFactory;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.ProductoDAO;

public class DAOJPAFactory implements DAOFactory {

	public PersonaDAO getPersonaDAO() {

		return new PersonaDAOImpl();
	}

	public PerfilDAO getPerfilDAO() {

		return new PerfilDAOImpl();
	}

	public ProductoDAO getProductoDAO() {

		return new ProductoDAOImpl();
	}

}
