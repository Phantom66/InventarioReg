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
			
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			 PersonaDAOImpl persona = new PersonaDAOImpl();
			 
			 int pagActual;
			 int perReg = 5;
			 
			 if(request.getParameter("pagActual")==null) {
				 
					pagActual = 1;
					//perReg = 3;
			 }else {
				 
				 
				 pagActual = Integer.valueOf(request.getParameter("pagActual"));
				 //perReg = 3;
			 }
			 

			
			List<Persona>perPagination = persona.getPerPagination(pagActual, perReg);
			 
			 //N° de filas de nuetra tabla.
			 int rows = persona.getRows();
			 
			 int nPages = rows / perReg; 

				if(nPages%perReg >0) {
					
					nPages ++;
				}
			
			request.setAttribute("nPages", nPages);
			request.setAttribute("pagActual", pagActual);
			request.setAttribute("perPage", perReg);
			request.setAttribute("Lista_Productos",perPagination);
			 
			 dispatcher = request.getRequestDispatcher("/principal.jsp"); 
			 dispatcher.forward(request, response);
			
			String user = (String)session.getAttribute("sessionUsuario");
			System.out.println("Sessión "+ user);
			
		}else {
		
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			System.out.println("Sessión "+ (String)session.getAttribute("sessionUsuario"));
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
	


	
}
