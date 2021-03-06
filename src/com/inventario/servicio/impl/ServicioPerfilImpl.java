package com.inventario.servicio.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.inventario.bo.Perfil;
import com.inventario.dao.PerfilDAO;
import com.inventario.servicio.ServicioPerfil;

public class ServicioPerfilImpl implements ServicioPerfil {

	private PerfilDAO perfil = null;


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

	@Override
	public PerfilDAO getPerfilDAO() {
		// TODO Auto-generated method stub
		return this.perfil;
	}

	@Override
	public void setPerfilDAO(PerfilDAO perfil) {
		
		this.perfil = perfil;
		
	}

}
