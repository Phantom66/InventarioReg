package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventario.acciones.AccionPrincipal;



public class ControladorActualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("Estoy actualizando");
		RequestDispatcher despachador = request.getRequestDispatcher("/principal.do");
		despachador.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AccionPrincipal main = new AccionPrincipal();
		main.getActualizarPost(request, response);
	}

}
