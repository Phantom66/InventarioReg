package com.inventario.con;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import com.inventario.dao.PersonaDAO;
import com.inventario.dao.imp.PersonaDAOImpl;

public class ControladorBorrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonaDAO persona = new PersonaDAOImpl();

		String cedula = request.getParameter("cedula");
		
		
		System.out.println("Persona que será eliminada " + cedula);
		persona.borrar(cedula);
		RequestDispatcher dispatcher = request.getRequestDispatcher("principal.do");
		
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
