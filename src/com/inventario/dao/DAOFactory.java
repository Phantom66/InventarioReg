package com.inventario.dao;

public interface DAOFactory {

	public abstract PersonaDAO getPersonaDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract PerfilDAO getPerfilDAO();
}
