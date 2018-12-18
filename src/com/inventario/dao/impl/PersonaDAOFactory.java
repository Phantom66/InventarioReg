package com.inventario.dao.impl;

/**
 * Cumpliendo con el principio IoC
 * Mejorar si se quiere crear más genéi
 * @author lilibeth
 *
 */
public class PersonaDAOFactory {

	public static PersonaDAOImpl getInstance() {
		
		return new PersonaDAOImpl();
	}
}