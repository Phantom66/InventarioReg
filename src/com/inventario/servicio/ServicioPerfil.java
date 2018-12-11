package com.inventario.servicio;

import java.util.List;

import com.inventario.bo.Perfil;

public interface ServicioPerfil {
	
	public abstract void insertar(Perfil perfil);
	public abstract void salvar(Perfil perfil);
	public abstract void borrar(Perfil perfil);
	
	public abstract List<Perfil>buscarTodos();
	public abstract Perfil buscarPorClave(String id);

}
