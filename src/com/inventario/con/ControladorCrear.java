package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ControladorCrear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			dispatcher = request.getRequestDispatcher("/frm.jsp");
			dispatcher.forward(request, response);
			
			
		}else {
			
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	
}
