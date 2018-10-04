package com.inventario.con;

/**
 * Se crea para mejorar nuestra captura de Excepciones
 * 
 * cambiamos de Exception a RuntimeException para simplificar el flujo de
 * excepcion ya que RuntimeException no nos obliga a lanzarlas.
 * 
 * Fuente: Arquitectura Java
 * 
 * @author ALeotur
 *
 */
public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseException() {
		super();
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseException(String message) {
		super(message);
	}

	public DataBaseException(Throwable cause) {

		super(cause);
	}
}
