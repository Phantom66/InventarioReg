package com.inventario.servicio;

import java.util.List;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.PersonaDAO;

/**
 * Esta capa de servicio es muy parecida al patrón
 * FACADE, verificar
 * 
 * @author ALeotur
 *
 */
public interface ServicioPersona {
	
	public abstract void insertar(Persona persona);
	public abstract void salvar(Persona persona);
	public abstract void borrar(Persona persona);
	public List<Persona>buscarTodos();
	public abstract Persona buscarPorClave(String cedula);
	public abstract Long getRows();
	public abstract List<Producto>getPerPagination(int pagActual, int perReg);

	public PersonaDAO getPersonaDAO();
	public void setPersonaDAO(PersonaDAO persona);
}
