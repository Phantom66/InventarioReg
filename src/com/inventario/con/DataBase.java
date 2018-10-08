package com.inventario.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private static final String PASSWORD = "123";

	private Connection conn = null;

	/*
	 * Aplicar el Patrón Singleton para conexión. Verificar esto.
	 */
	public DataBase() {
	};


	public Connection getConnection(){

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			System.out.println("Abriendo Conexion");
			return this.conn;
			
		} catch (ClassNotFoundException e) {

			System.out.println("Clase no encontrada " + e.getMessage());
			throw new DataBaseException("Clase No Encontrada ", e);

		} catch (SQLException e) {
			
			System.out.println("Error de SQL data " + e.getMessage());
			throw new DataBaseException("Error SQL ", e);

		}

	}


	public void closeConnection() throws DataBaseException{

		try {

			if (this.conn != null) {
				this.conn.close();
				System.out.println("Cerrando Conexión");

			} else {

				System.out.println("No hay conexión");
			}

		} catch (SQLException e) {
			
			throw new DataBaseException("Error de conexión " + e);
		}
	}

}
