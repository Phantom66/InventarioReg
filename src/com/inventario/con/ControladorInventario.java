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

import com.inventario.acciones.AccionPrincipal;
import com.inventario.bo.Persona;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.imp.PersonaDAOImpl;

/**
 * Servlet implementation class ControladorInventario
 */
@WebServlet("/ControladorInventario")
public class ControladorInventario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccionPrincipal main = new AccionPrincipal();	
		main.getPrincipal(request, response);

	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	
}
