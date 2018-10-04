package com.inventario.dao;

import java.sql.SQLException;
import java.util.List;
import com.inventario.bo.Perfil;


public interface PerfilDAO {
	
	//Coloco esto para que devuelva el id de la inserción
	public abstract void insertar(Perfil perfil)throws SQLException, ClassNotFoundException;
	public abstract void salvar(Perfil perfil)throws SQLException, ClassNotFoundException;
	public abstract void borrar(String perfil)throws SQLException, ClassNotFoundException;
	
	public abstract List<Perfil>buscarTodos()throws SQLException, ClassNotFoundException;
	public abstract Perfil buscarPorClave(String id)throws SQLException, ClassNotFoundException;

}
