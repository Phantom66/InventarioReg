package com.inventario.servicio.impl;

import java.util.List;

import com.inventario.bo.Perfil;
import com.inventario.dao.DAOFactory;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.impl.DAOAbstractFactory;
import com.inventario.servicio.ServicioPerfil;

public class ServicioPerfilImpl implements ServicioPerfil {

	PerfilDAO perfil = null;

	public ServicioPerfilImpl() {

		DAOFactory factoria = DAOAbstractFactory.getInstance();
		perfil = factoria.getPerfilDAO();
	}

	@Override
	public void insertar(Perfil perfil) {
		
		this.perfil.insertar(perfil);

	}

	@Override
	public void salvar(Perfil perfil) {
		
		this.perfil.salvar(perfil);

	}

	@Override
	public void borrar(Perfil perfil) {
		
		this.perfil.borrar(perfil);

	}

	@Override
	public List<Perfil> buscarTodos() {
		
		return this.perfil.buscarTodos();
	}

	@Override
	public Perfil buscarPorClave(String id) {

		return this.perfil.buscarPorClave(id);
	}

}
