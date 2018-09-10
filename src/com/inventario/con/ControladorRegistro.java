package com.inventario.con;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.imp.PersonaDAOImpl;
import com.inventario.dao.imp.ProductoDAOImpl;



public class ControladorRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");
		
		String producto = request.getParameter("producto");
		String estatus = request.getParameter("status");
		String descripcion = request.getParameter("descripcion");
		
		//PersonaDAOImpl insertar = new PersonaDAOImpl();
		
		//insertar.insertar(new Persona(cedula,nombre,apellidos, telefono));
		
		ProductoDAOImpl product = new ProductoDAOImpl();
		product.insertar(new Producto(0, producto,estatus,descripcion));
		
	}

}
