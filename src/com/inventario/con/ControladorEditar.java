package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControladorEditar")
public class ControladorEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cedula = request.getParameter("cedula");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/editar.jsp"); ;

		request.setAttribute("cedula", cedula);
		dispatcher.forward(request, response);

		System.out.println(cedula);
	}

}
