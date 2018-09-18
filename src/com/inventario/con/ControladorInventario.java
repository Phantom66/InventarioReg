package com.inventario.con;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventario.bo.Persona;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.imp.PersonaDAOImpl;

/**
 * Servlet implementation class ControladorInventario
 */
@WebServlet("/ControladorInventario")
public class ControladorInventario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			 PersonaDAO persona = new PersonaDAOImpl();
			 List<Persona> personas = persona.buscarTodos();
			 request.setAttribute("Lista_Productos",personas);
			 dispatcher = request.getRequestDispatcher("/principal.jsp"); 
			 dispatcher.forward(request, response);
			
			String user = (String)session.getAttribute("sessionUsuario");
			System.out.println("Sessión "+ user);
			
		}else {
		
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			System.out.println("Sessión "+ (String)session.getAttribute("sessionUsuario"));
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
	


}
