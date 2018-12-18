package com.inventario.servicio.impl;

import java.util.List;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.impl.PersonaDAOImpl;
import com.inventario.servicio.ServicioPersona;

public class ServicioPersonaImpl implements ServicioPersona {
	
	PersonaDAO persona = null;
	
	public ServicioPersonaImpl() {
		//DAOFactory factoria = DAOAbstractFactory.getInstance();
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("contextoAplicacion.xml");
				
		persona = (PersonaDAO)factoria.getBean("personaDAO");
	}

	@Override
	public void insertar(Persona persona) {
		
		this.persona.insertar(persona);
		
	}

	@Override
	public void salvar(Persona persona) {
		
		this.persona.salvar(persona);
		
	}

	@Override
	public void borrar(Persona persona) {
		
		this.persona.borrar(persona);
	}

	@Override
	public List<Persona> buscarTodos() {
		
		return persona.buscarTodos();
	}

	@Override
	public Persona buscarPorClave(String cedula) {
		
		return this.persona.buscarPorClave(cedula);
	}

	@Override
	public List<Producto> getPerPagination(int pagActual, int perReg) {
		
		PersonaDAOImpl persona = new PersonaDAOImpl();
		return persona.getPerPagination(pagActual, perReg);
	}

	/**
	 * Mejorar este método,
	 * lo realicé de esta manera debido a que es un método
	 * de la clase PersonaDAOImpl y no se encuentra en la interfaz
	 */
	@Override
	public Long getRows() {
		
		PersonaDAOImpl persona = new PersonaDAOImpl();
		return persona.getRows();
	}

}
