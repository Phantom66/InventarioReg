package com.inventario.dao;

import java.sql.SQLException;
import java.util.List;

import com.inventario.bo.Persona;

public interface PersonaDAO {

	//Coloco esto para que devuelva el id de la inserción
	public abstract void insertar(Persona persona)throws SQLException, ClassNotFoundException;
	public abstract void salvar(Persona persona)throws SQLException, ClassNotFoundException;
	public abstract void borrar(String cedula)throws SQLException, ClassNotFoundException;
	
	public List<Persona>buscarTodos()throws SQLException, ClassNotFoundException;
	public abstract Persona buscarPorClave(String id)throws SQLException, ClassNotFoundException;
	
	
}
