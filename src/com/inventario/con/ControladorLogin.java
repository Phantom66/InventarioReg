package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventario.acciones.AccionPrincipal;

public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccionPrincipal accion = new AccionPrincipal();
		
		String route = accion.getLoggin(request, response);
		System.out.println("Estoy aqui cerrando " + route);
		dispatcher = request.getRequestDispatcher(route);
		dispatcher.forward(request, response);

	}

}
