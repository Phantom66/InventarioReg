package com.inventario.servicio;

import java.util.List;

import com.inventario.bo.Perfil;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.PersonaDAO;

public interface ServicioPerfil {
	
	public abstract void insertar(Perfil perfil);
	public abstract void salvar(Perfil perfil);
	public abstract void borrar(Perfil perfil);
	
	public abstract List<Perfil>buscarTodos();
	public abstract Perfil buscarPorClave(String id);
	
	public PerfilDAO getPerfilDAO();
	public void setPerfilDAO(PerfilDAO perfil);

}
