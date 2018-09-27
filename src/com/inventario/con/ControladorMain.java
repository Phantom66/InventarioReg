package com.inventario.con;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.acciones.AccionPrincipal;

public class ControladorMain extends HttpServlet {

	
	AccionPrincipal main = new AccionPrincipal();
	String url;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		url = request.getServletPath();
		main.getAccion(url.substring(1, url.length() - 3), request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		url = request.getServletPath();
		main.getAccion(url.substring(1, url.length() - 3), request, response);

	}
	

	

}
