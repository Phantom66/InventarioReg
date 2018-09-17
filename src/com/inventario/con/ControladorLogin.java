package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String anonymous = "anonymous";
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		
		
		if(session.getAttribute("sessionUsuario")==null) {
			
			System.out.println("  " + session.getAttribute("sessionUsuario"));
				if(email !=null && pass !=null ) {
					
					session.setAttribute("sessionUsuario", anonymous);
					
					dispatcher = request.getRequestDispatcher("/principal.do");
					dispatcher.forward(request, response);
					
					
				}else {
					
					dispatcher = request.getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
					
				}
								
			
		}else {
		
			System.out.println("Eliminando sessiion");
			session.removeAttribute("sessionUsuario");
			
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			
		}
				
		

		System.out.println("Usuario " + email + " Password " + pass + request.getAttribute("sessionUsuario"));
	}

}
