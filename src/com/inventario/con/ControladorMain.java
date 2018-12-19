package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.acciones.AccionPrincipal;

public class ControladorMain extends HttpServlet {

	
	AccionPrincipal main = new AccionPrincipal();
	String url;
	String route;
	RequestDispatcher dispatcher  = null;
	
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doHandle(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doHandle(request, response);

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		url = request.getServletPath().substring(1,request.getServletPath().length()-3);
		route = main.getAccion(url, request, response);
		
		dispatcher = request.getRequestDispatcher(route);
		dispatcher.forward(request, response);
	}
	

	

}
