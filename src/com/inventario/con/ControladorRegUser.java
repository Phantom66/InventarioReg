package com.inventario.con;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventario.acciones.AccionPrincipal;

public class ControladorRegUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		AccionPrincipal main = new AccionPrincipal();
		main.getRegUserPost(request, response);

	}

}
