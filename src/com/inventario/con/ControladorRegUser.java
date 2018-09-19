package com.inventario.con;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventario.bo.Perfil;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.imp.PerfilDAOImpl;
import com.inventario.utils.SecurityPasswords;

public class ControladorRegUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		
		String name = request.getParameter("user");
		String email = request.getParameter("email");
		String password = SecurityPasswords.Encriptar(request.getParameter("pass"));;
		String pass = SecurityPasswords.Encriptar(request.getParameter("passConfirm"));

		PerfilDAO buscarPerfil = new PerfilDAOImpl();
		Perfil perfil = buscarPerfil.buscarPorClave(email);

		if (perfil != null) {
			
			try {
				System.out.println("Contraseña No Coinciden.");
				request.setAttribute("mensageError", "Perfil Existe.");
				request.getRequestDispatcher("/registro_user.jsp").forward(request, response);

			} catch (ServletException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {

			try {
				System.out.println("perfil no existe");

				if (password.equalsIgnoreCase(pass)) {

					perfil = new Perfil(0, name, email, password);
					buscarPerfil.insertar(perfil);
					request.setAttribute("messageSuccess", "Registro Exitoso!!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);

				} else {
					
					request.setAttribute("mensageError", "Contraseña No Coinciden.");
					request.getRequestDispatcher("/registro_user.jsp").forward(request, response);
				}

			} catch (ServletException e) {

				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
