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
		
		String url = request.getServletPath();
		main.getAccion(url.substring(1, url.length() - 3), request, response);

		// if (request.getServletPath().equals("/Principal.do")) {
		//
		// main.getPrincipal(request, response);
		//
		// } else if (request.getServletPath().equals("/Editar.do")) {
		//
		// main.getEditar(request, response);
		//
		// } else if (request.getServletPath().equals("/Crear.do")) {
		//
		// main.getCrear(request, response);
		//
		// } else if (request.getServletPath().equals("/Borrar.do")) {
		//
		// main.getBorrar(request, response);
		//
		// } else {
		//
		// System.out.println("No existe---" + url);
		// }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getServletPath();
		main.getAccion(url.substring(1, url.length() - 3), request, response);
		
		// if (request.getServletPath().equals("/Principal.do")) {
		//
		// main.getPrincipal(request, response);
		//
		// }else if(request.getServletPath().equals("/Actualizar.do")){
		//
		// main.getActualizar(request, response);
		//
		// }else if(request.getServletPath().equals("/Registro.do")){
		//
		// main.getRegistrar(request, response);
		//
		// }else if(request.getServletPath().equals("/Reg_User.do")){
		//
		// main.getRegUser(request, response);
		//
		// }else {
		// System.out.println("Estoy en la ---" + url);
		// }
	}
	

	

}
