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
	private static final String PASSWORD = "";

	private Connection conn = null;

	/*
	 * Aplicar el Patrón Singleton para conexión. Verificar esto.
	 */
	public DataBase() {
	};

	/**
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			System.out.println("Abriendo Conexion");
			return this.conn;
			
		} catch (ClassNotFoundException e) {

			System.out.println("Clase no encontrada !!");

			throw e;
			// e.printStackTrace();

		} catch (SQLException e) {
			
			System.out.println("Error en los datos de la conexión. ");
			throw e;
			// e.printStackTrace();
		}

	}

	/**
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {

		try {

			if (this.conn != null) {
				this.conn.close();
				System.out.println("Cerrando Conexión");

			} else {

				System.out.println("No hay conexión");
			}

		} catch (SQLException e) {
			
			throw e;
		}
	}

}
