package com.inventario.con;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
		PersonaDAO persona = new PersonaDAOImpl();
		List<Persona> personas = persona.buscarTodos();
		request.setAttribute("Lista_Productos",personas);
		RequestDispatcher miDispatcher = request.getRequestDispatcher("/resources/principal.jsp"); 
		miDispatcher.forward(request, response);
		
	}



}
