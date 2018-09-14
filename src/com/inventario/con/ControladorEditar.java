package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.ProductoDAO;
import com.inventario.dao.imp.PersonaDAOImpl;
import com.inventario.dao.imp.ProductoDAOImpl;


@WebServlet("/ControladorEditar")
public class ControladorEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cedula = request.getParameter("cedula");
		
		PersonaDAO persona = new PersonaDAOImpl();
		ProductoDAO producto = new ProductoDAOImpl();
		
		Persona encontrada  = persona.buscarPorClave(cedula);
		Producto encontrado = producto.buscarPorClave(cedula);

		System.out.println(cedula + encontrada + "\n" +encontrado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editar.jsp"); ;

		request.setAttribute("encontrada", encontrada);
		request.setAttribute("encontrado", encontrado);
		dispatcher.forward(request, response);

		
	}

}
