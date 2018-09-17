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

		String name = request.getParameter("user");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String pass = request.getParameter("passConfirm");

		PerfilDAO buscarPerfil = new PerfilDAOImpl();
		Perfil perfil = buscarPerfil.buscarPorClave(email);

		if (perfil != null) {

			System.out.println("Perfil existe " + perfil.getEmail());
			try {
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

					System.out.println("Clase coinciden");
					perfil = new Perfil(0, name, email, password);
					buscarPerfil.insertar(perfil);
					request.getRequestDispatcher("/login.jsp").forward(request, response);

				} else {

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
