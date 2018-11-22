package com.inventario.con;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 
 * @author ALeotur
 *
 */
public class HibernateHelper {
	
	private static final SessionFactory sessionFactoria = buildSessionFactory();
	
	/**
	 * Iniciamos el Framework y devolvemos la Factoría.
	 * @return SessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		
		try {
			return new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable ex) {
			
			System.err.println("Error creando una factoria de session." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		
		return sessionFactoria;
	}

}
