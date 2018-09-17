package com.inventario.con;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.bo.Perfil;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.imp.PerfilDAOImpl;


public class ControladorRegUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		
		PerfilDAO buscarPerfil = new PerfilDAOImpl();
		
		Perfil perfil = buscarPerfil.buscarPorClave(request.getParameter("email"));
		
		
		System.out.println(perfil);
		
		if(perfil != null) {
			
			System.out.println("Perfil existe " +  perfil.getEmail());
			
			
		}else {
			
			System.out.println("perfil no existe");
			
			try {
				request.getRequestDispatcher("/registro_user.jsp").forward(request, response);
				
			} catch (ServletException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
