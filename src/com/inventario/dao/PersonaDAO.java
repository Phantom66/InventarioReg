package com.inventario.dao;

import java.util.List;

import com.inventario.bo.Persona;

public interface PersonaDAO {

	public abstract void insertar(Persona persona);
	public abstract void salvar(Persona persona);
	public abstract void borrar(Persona persona);
	
	public List<Persona>buscarTodos();
	public abstract void buscarPorClave(int id);
	
	
}
