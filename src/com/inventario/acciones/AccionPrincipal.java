package com.inventario.acciones;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventario.bo.Perfil;
import com.inventario.bo.Persona;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.imp.PerfilDAOImpl;
import com.inventario.dao.imp.PersonaDAOImpl;
import com.inventario.utils.SecurityPasswords;

public class AccionPrincipal {
	
	
	public AccionPrincipal() {}

	public void getPrincipal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		if (session.getAttribute("sessionUsuario") != null) {
			PersonaDAOImpl persona = new PersonaDAOImpl();

			int pagActual;
			int perReg = 5;

			if (request.getParameter("pagActual") == null) {

				pagActual = 1;
				// perReg = 3;
			} else {

				pagActual = Integer.valueOf(request.getParameter("pagActual"));
				// perReg = 3;
			}

			List<Persona> perPagination = persona.getPerPagination(pagActual, perReg);

			// N° de filas de nuetra tabla.
			int rows = persona.getRows();
			int nPages = rows / perReg;
			if (nPages % perReg > 0) {

				nPages++;
			}

			request.setAttribute("nPages", nPages);
			request.setAttribute("pagActual", pagActual);
			request.setAttribute("perPage", perReg);
			request.setAttribute("Lista_Productos", perPagination);

			dispatcher = request.getRequestDispatcher("/principal.jsp");
			dispatcher.forward(request, response);

			String user = (String) session.getAttribute("sessionUsuario");
			System.out.println("Sessión " + user);

		} else {

			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			System.out.println("Sessión " + (String) session.getAttribute("sessionUsuario"));

		}

	}
	
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getLoggin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		Perfil perfil = null;
		PerfilDAO buscarPerfil;

		System.out.println("  " + session.getAttribute("sessionUsuario") + " " + request.getParameter("cerrarSession"));

		if ((session.getAttribute("sessionUsuario") == null) && (request.getParameter("cerrarSession") == null)) {

			if (email != null && pass != null) {

				buscarPerfil = new PerfilDAOImpl();
				perfil = buscarPerfil.buscarPorClave(email);

				System.out.println(perfil);

				if (perfil != null) {

					System.out.println(pass + " " + perfil.getPassword());

					if (email.equalsIgnoreCase(perfil.getEmail())
							&& SecurityPasswords.encriptar(pass).equalsIgnoreCase(perfil.getPassword())) {

						session.setAttribute("sessionUsuario", perfil.getName());

						dispatcher = request.getRequestDispatcher("/principal.do");
						dispatcher.forward(request, response);

					} else {

						request.setAttribute("mensageError", "Usuario o Contraseña Incorrecta");
						dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);

					}

				} else {

					request.setAttribute("mensageError", "Usuario no existe, debe registrarse");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}

			} else {

				dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);

			}

		} else {

			System.out.println("Eliminando Sessión");

			// Ver la differencia.
			// session.invalidate();
			session.removeAttribute("sessionUsuario");
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

		}

		System.out.println(
				"Usuario " + email + " Password " + pass + " " + request.getAttribute("sessionUsuario") + " " + perfil);
	}

}
