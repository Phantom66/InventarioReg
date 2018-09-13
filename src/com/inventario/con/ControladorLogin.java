package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		
		dispatcher = request.getRequestDispatcher("/principal.do");
		dispatcher.forward(request, response);

		System.out.println("Usuario " + email + " Password " + pass);
	}

}
