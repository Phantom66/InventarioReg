package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.ProductoDAO;
import com.inventario.dao.imp.PersonaDAOImpl;
import com.inventario.dao.imp.ProductoDAOImpl;

public class ControladorEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			String cedula = request.getParameter("cedula");

			PersonaDAO persona = new PersonaDAOImpl();
			ProductoDAO producto = new ProductoDAOImpl();

			Persona encontrada = persona.buscarPorClave(cedula);
			Producto encontrado = producto.buscarPorClave(cedula);

			System.out.println(cedula + encontrada + "\n" + encontrado);
			dispatcher = request.getRequestDispatcher("/editar.jsp");

			request.setAttribute("encontrada", encontrada);
			request.setAttribute("encontrado", encontrado);
			dispatcher.forward(request, response);
			
		}else {
			
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	

		String user = (String) session.getAttribute("sessionUsuario");
		System.out.println("Sessión " + user);

	}

}
