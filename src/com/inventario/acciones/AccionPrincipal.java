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
import com.inventario.bo.Producto;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.ProductoDAO;
import com.inventario.dao.imp.PerfilDAOImpl;
import com.inventario.dao.imp.PersonaDAOImpl;
import com.inventario.dao.imp.ProductoDAOImpl;
import com.inventario.utils.SecurityPasswords;


/**
 * Clase creada para obtener las acciones de los Controladores.
 * 
 * @author ALeotur
 *
 */
public class AccionPrincipal {
	
	
	public AccionPrincipal() {}

	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getLogginPost(HttpServletRequest request, HttpServletResponse response)
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

						dispatcher = request.getRequestDispatcher("/principal.do?action=principal");
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
	
	public void getPrincipalGet(HttpServletRequest request, HttpServletResponse response)
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
	public void getActualizarPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersonaDAO persona = new PersonaDAOImpl();
		ProductoDAO product = new ProductoDAOImpl();
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");

		String producto = request.getParameter("producto");
		String estatus = request.getParameter("status");
		String descripcion = request.getParameter("descripcion");

		System.out.println(cedula + " " + " " + nombre + "\n");

		Persona per = new Persona(cedula, nombre, apellidos, telefono);
		Producto pro = new Producto(0, producto, estatus, descripcion, per);

		persona.salvar(per);
		product.salvar(pro);
		System.out.print("Estoy actualizando");
		RequestDispatcher despachador = request.getRequestDispatcher("/principal.do");
		despachador.forward(request, response);		
		//controller.doGet(request, response);
	}

	
	/**
	 * Debería ser enviado por método Post o Delete verificar
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getBorrarGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersonaDAO persona = new PersonaDAOImpl();

		String cedula = request.getParameter("cedula");

		System.out.println("Persona que será eliminada " + cedula);
		persona.borrar(cedula);
		RequestDispatcher dispatcher = request.getRequestDispatcher("principal.do");

		dispatcher.forward(request, response);
	}


	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCrearGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			dispatcher = request.getRequestDispatcher("/frm.jsp?action=form");
			dispatcher.forward(request, response);
			
			
		}else {
			
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getEditarGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		if(session.getAttribute("sessionUsuario")!=null) {
			
			String cedula = request.getParameter("cedula");

			PersonaDAO persona = new PersonaDAOImpl();
			ProductoDAO producto = new ProductoDAOImpl();

			Persona encontrada = persona.buscarPorClave(cedula);
			Producto encontrado = producto.buscarPorClave(cedula);

			System.out.println(cedula + encontrada + "\n" + encontrado);
			dispatcher = request.getRequestDispatcher("/editar.jsp");

			request.setAttribute("encontrada", encontrada);
			request.setAttribute("encontrado", encontrado);
			dispatcher.forward(request, response);
			
		}else {
			
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	

		String user = (String) session.getAttribute("sessionUsuario");
		System.out.println("Sessión " + user);
	}


	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getRegistrPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");

		String producto = request.getParameter("producto");
		String estatus = request.getParameter("status");
		String descripcion = request.getParameter("descripcion");

		PersonaDAOImpl insertar = new PersonaDAOImpl();
		ProductoDAOImpl product = new ProductoDAOImpl();
		
		// int id = insertar.insertar(new Persona(cedula,nombre,apellidos, telefono));
		insertar.insertar(new Persona(cedula,nombre,apellidos, telefono));
		product.insertar(new Producto(0, producto, estatus, descripcion), cedula);
		
		System.out.print("Estoy registrando");
		RequestDispatcher  despachador = request.getRequestDispatcher("/principal.do");
		despachador.forward(request, response);
	}


	/**
	 * @param request
	 * @param response
	 */
	public void getRegUserPost(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("user");
		String email = request.getParameter("email");
		String password = SecurityPasswords.encriptar(request.getParameter("pass"));;
		String pass = SecurityPasswords.encriptar(request.getParameter("passConfirm"));

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
					
					request.setAttribute("messageError", "Contraseña No Coinciden.");
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
