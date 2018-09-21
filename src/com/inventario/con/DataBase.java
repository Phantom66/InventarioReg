package com.inventario.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Creando esta Clase para crear la coneción y cumplir con el 
 * principio de DRY
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
	
	/*
	 * Aplicar el Patrón Singleton para conexión. Verificar esto.
	 */
	public DataBase(){	};
	
	/**
	 * @return Connection
	 */
	
	public Connection getConnection() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("Abriendo conexion");
		return this.conn;
	}
	
	public void closeConnection() {

		try {

			if (this.conn != null) {
				this.conn.close();
				System.out.println("cerrando conexión");

			} else {

				System.out.println("No hay conexión");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
