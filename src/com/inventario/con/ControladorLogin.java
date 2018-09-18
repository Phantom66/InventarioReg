package com.inventario.con;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventario.bo.Perfil;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.imp.PerfilDAOImpl;
import com.inventario.utils.SecurityPasswords;


public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		Perfil perfil = null;
		PerfilDAO buscarPerfil;

		System.out.println("  " + session.getAttribute("sessionUsuario") + " "+ request.getParameter("cerrarSession"));
		
		if ((session.getAttribute("sessionUsuario") == null) && (request.getParameter("cerrarSession")==null)) {

			if (email != null && pass != null) {

				buscarPerfil = new PerfilDAOImpl();
				perfil = buscarPerfil.buscarPorClave(email);
				
				
				System.out.println(perfil);
				
				if (perfil != null) {
	
					System.out.println(pass + " "+ perfil.getPassword());

					if (email.equalsIgnoreCase(perfil.getEmail()) && SecurityPasswords.Encriptar(pass).equalsIgnoreCase(perfil.getPassword())) {

						session.setAttribute("sessionUsuario", perfil.getName());

						dispatcher = request.getRequestDispatcher("/principal.do");
						dispatcher.forward(request, response);

					} else {
						System.out.println("Usuario o Contraseña Incorrecta");

						dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);

					}
					
				} else {

					request.getRequestDispatcher("/registro_user.jsp").forward(request, response);
					System.out.println("Usuario no existe, debe registrarse");
				}

			} else {

				dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);

			}

		} else {

			System.out.println("Eliminando sessiion");
			session.removeAttribute("sessionUsuario");

			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

		}

		System.out.println(
				"Usuario " + email + " Password " + pass + " " + request.getAttribute("sessionUsuario") + " " + perfil);
	}

}
