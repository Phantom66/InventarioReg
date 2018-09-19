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


public class ControladorActualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		System.out.print("Estoy actualizando");
		RequestDispatcher  despachador = request.getRequestDispatcher("/principal.do");
		despachador.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		PersonaDAO persona = new PersonaDAOImpl();
		ProductoDAO product = new ProductoDAOImpl();
		int cedula = Integer.parseInt(request.getParameter("cedula"));	
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");

		String producto = request.getParameter("producto");
		String estatus = request.getParameter("status");
		String descripcion = request.getParameter("descripcion");
		
		System.out.println(cedula + " "+" " + nombre + "\n");
		
		Persona per = new Persona(cedula,nombre,apellidos,telefono);
		Producto pro = new Producto(0,producto,estatus,descripcion, per);
		
		
		
		 persona.salvar(per);
		 product.salvar(pro);
		
		
		doGet(request, response);
	}

}
