package com.inventario.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 
 * Creando esta Clase para crear la conexión y cumplir con el principio de DRY
 * 
 * @author phantom
 *
 */
public class DataBase {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/Inventario";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private Connection conn = null;

	//Sustituye la consola(System.out.print) por el Log para arrojar mensajes.
	private static final Logger log = Logger.getLogger(DataBase.class.getPackage().getName());
	/*
	 * Aplicar el Patrón Singleton para conexión. Verificar esto.
	 */
	public DataBase() {
	};


	public Connection getConnection(){

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			log.info("Abriendo Conexion");
			return this.conn;
			
		} catch (ClassNotFoundException e) {

			log.warning("Clase no encontrada " + e.getMessage());

			throw new DataBaseException("Clase No Encontrada ", e);

		} catch (SQLException e) {
			
			log.warning("Error de SQL data " + e.getMessage());
			throw new DataBaseException("Error SQL ", e);

		}

	}


	public void closeConnection() throws DataBaseException{

		try {

			if (this.conn != null) {
				this.conn.close();
				log.info("Cerrando Conexión");

			} else {
				log.info("No hay conexión");

			}

		} catch (SQLException e) {
			
			log.warning("Error de conexión " + e.getMessage());
			throw new DataBaseException("Error de conexión " + e);
		}
	}

}
