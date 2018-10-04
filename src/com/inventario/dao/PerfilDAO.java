package com.inventario.dao;

import java.util.List;
import com.inventario.bo.Perfil;
import com.inventario.con.DataBaseException;



public interface PerfilDAO {
	
	//Coloco esto para que devuelva el id de la inserción
	public abstract void insertar(Perfil perfil);
	public abstract void salvar(Perfil perfil);
	public abstract void borrar(String perfil);
	
	public abstract List<Perfil>buscarTodos();
	public abstract Perfil buscarPorClave(String id);

}
