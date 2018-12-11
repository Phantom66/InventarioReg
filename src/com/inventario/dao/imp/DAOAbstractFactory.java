package com.inventario.dao.imp;

import com.inventario.dao.DAOFactory;

/**
 * Abstract Factory, aquí podemos agregar otros
 * objetos que nos permita flexibilizar nuestras
 * creación de objetos con la finalidad de cumplir
 * con el patrón IoC
 * 
 * @author ALeotur
 *
 */
public abstract class DAOAbstractFactory {

	
	public static DAOFactory getInstance() {
		
		return new DAOJPAFactory();
	}
}
