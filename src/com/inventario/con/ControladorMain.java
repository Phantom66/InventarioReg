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
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (getUrl(request).equals("/principal.do")) {

			main.getPrincipalGet(request, response);
			
		}else if(getUrl(request).equals("/editar.do")){
			
			main.getEditarGet(request, response);
			
		}else {
			
			System.out.println("Estoy en la ---" + url);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (getUrl(request).equals("/principal.do")) {

			main.getPrincipalGet(request, response);
			
		}else if(getUrl(request).equals("/actualizar.do")){
			
			main.getActualizarPost(request, response);
		
		}else {
			System.out.println("Estoy en la ---" + url);
		}
	}
	
	public String getUrl(HttpServletRequest request) {

		url = request.getRequestURL().substring(35);
		return url;
	}
	

}
