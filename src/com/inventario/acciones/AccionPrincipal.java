package com.inventario.acciones;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inventario.bo.Perfil;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.con.DataBaseException;
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

	HttpSession session;

	public AccionPrincipal() {
	}

	/**
	 * Método que utiliza el reflection de Java para ejecutar los métodos de la
	 * clase y así evitar utilizar los if anidados.
	 * 
	 * @param accion
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public <T> String getAccion(String accion, HttpServletRequest request, HttpServletResponse response) {

		T obj;
		try {
			obj = (T) Class.forName("com.inventario.acciones.AccionPrincipal").newInstance();

			Method[] metodos = obj.getClass().getDeclaredMethods();

			for (int i = 0; i < metodos.length; i++) {

				if (metodos[i].getName().substring(3).equals(accion)) {

					return (String) metodos[i].invoke(obj, request, response);

				}

			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getLoggin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		session = request.getSession();
		RequestDispatcher dispatcher = null;
		Perfil perfil = new Perfil();
		PerfilDAO buscarPerfil;

		// System.out.println(" " + session.getAttribute("sessionUsuario") + " " +
		// request.getParameter("cerrarSession"));
		// session.getAttribute("sessionUsuario") quitando para realizar prueba con
		// validacones de contenedores
		if ((session.getId() == null) && (request.getParameter("cerrarSession") == null)) {

			// System.out.println("Llego hasta aquí --->" + email + " --- "+pass);
			if (email != null && pass != null) {

				// Solo para probar para capturar las excepciones.
				try {
					buscarPerfil = new PerfilDAOImpl();
					perfil = buscarPerfil.buscarPorClave(email);

				} catch (NullPointerException e) {

					throw new DataBaseException("Correo no encontrado ", e);

				}
				System.out.println("Estoy aquí ----" + perfil);

				if (perfil != null) {

					System.out.println(pass + " " + perfil.getPassword() + " Estoy en el Loggin");

					if (email.equalsIgnoreCase(perfil.getEmail())
							&& SecurityPasswords.encriptar(pass).equalsIgnoreCase(perfil.getPassword())) {

						session.setAttribute("sessionUsuario", perfil.getName());

						dispatcher = request.getRequestDispatcher("/Principal.do");
						dispatcher.forward(request, response);

					} else {

						request.setAttribute("messageError", "Usuario o Contraseña Incorrecta");
						dispatcher = request.getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);

					}

				} else {

					request.setAttribute("messageError", "Usuario no existe, debe registrarse");
					dispatcher = request.getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}

			} else {

				dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);

			}

		} else {

			System.out.println("Eliminando Sessión");

			// Ver la differencia.
			session.invalidate();
			//session.removeAttribute("sessionUsuario");
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

		}

		System.out.println(
				"Usuario " + email + " Password " + pass + " " + request.getAttribute("sessionUsuario") + " " + perfil);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String getPrincipal(HttpServletRequest request, HttpServletResponse response) {

		session = request.getSession();

		// if (session.getAttribute("sessionUsuario") != null)
		if (session.getId() != null) {
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

			List<Producto> perPagination = persona.getPerPagination(pagActual, perReg);

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

				System.out.println("user" + session.getId() + perPagination.toString());

			return "principal/principal.jsp";

		} else {

			System.out.println("Sessión " + (String) session.getAttribute("sessionUsuario"));
			return "/login.jsp";

		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String getActualizar(HttpServletRequest request, HttpServletResponse response) {
		PersonaDAO persona = new PersonaDAOImpl();
		ProductoDAO product = new ProductoDAOImpl();

		Persona per = new Persona(Integer.parseInt(request.getParameter("cedula")), request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("telefono"));
		// Mejorar
		Producto pro = new Producto(0, request.getParameter("producto"), request.getParameter("status"),
				request.getParameter("descripcion"), per);

		persona.salvar(per);
		product.salvar(pro);

		System.out.print("Estoy actualizando " + per.getCedula() + " " + per.getNombre());

		return "/Principal.do";

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String getBorrar(HttpServletRequest request, HttpServletResponse response) {
		PersonaDAO persona = new PersonaDAOImpl();

		System.out.println("Persona que será eliminada " + request.getParameter("cedula"));
		persona.borrar(request.getParameter("cedula"));

		return "Principal.do";

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String getCrear(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// if (session.getAttribute("sessionUsuario") != null)
		if (session.getId() != null) {

			return "principal/frm.jsp";

		} else {

			return "/login.jsp";

		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public String getEditar(HttpServletRequest request, HttpServletResponse response) {

		session = request.getSession();
		// if (session.getAttribute("sessionUsuario") != null)
		if (session.getId() != null) {

			String cedula = request.getParameter("id");

			PersonaDAO persona = new PersonaDAOImpl();
			ProductoDAO producto = new ProductoDAOImpl();

			Persona encontrada = persona.buscarPorClave(cedula);
			Producto encontrado = producto.buscarPorClave(cedula);

			System.out.println(cedula + encontrada + "\n" + encontrado);

			request.setAttribute("encontrada", encontrada);
			request.setAttribute("encontrado", encontrado);

			String user = (String) session.getAttribute("sessionUsuario");
			System.out.println("Sessión " + user);

			return "principal/editar.jsp";

		} else {
			String user = (String) session.getAttribute("sessionUsuario");
			System.out.println("Sessión " + user);
			return "/login.jsp";
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws DataBaseException
	 * @throws ServletException
	 */
	public String getRegistrar(HttpServletRequest request, HttpServletResponse response) {

		// Dejo el campo cédula debido a que lo utilizo en los dos objetos. Mejorar.
		// int cedula = Integer.parseInt(request.getParameter("cedula"));

		PersonaDAOImpl insertar = new PersonaDAOImpl();
		ProductoDAOImpl product = new ProductoDAOImpl();

		Persona persona = new Persona(Integer.parseInt(request.getParameter("cedula")), request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("telefono"));
		// int id = insertar.insertar(new Persona(cedula,nombre,apellidos, telefono));
		insertar.insertar(persona);

		product.insertar(new Producto(0, request.getParameter("producto"), request.getParameter("status"),
				request.getParameter("descripcion"), persona));

		System.out.print("Estoy registrando");

		return "/Principal.do";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getRegUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("user");
		String email = request.getParameter("email");
		String password = SecurityPasswords.encriptar(request.getParameter("pass"));
		String pass = SecurityPasswords.encriptar(request.getParameter("passConfirm"));

		PerfilDAO buscarPerfil = new PerfilDAOImpl();
		PersonaDAO insertarPersona = new PersonaDAOImpl();
		Perfil perfil = buscarPerfil.buscarPorClave(email);

		if (perfil != null) {

			System.out.println("Contraseña No Coinciden.");
			request.setAttribute("messageError", "Perfil Existe.");
			return "/registro_user.jsp";

		} else {

			System.out.println("perfil no existe");

			if (password.equalsIgnoreCase(pass)) {

				// Cédula de persona debe ser validada, por los momentos lo dejaré así para
				// realizar pruebas.
				Persona p = new Persona(Integer.parseInt(request.getParameter("cedula")),
						request.getParameter("nombre"), request.getParameter("apellido"),
						request.getParameter("telefono"));

				insertarPersona.insertar(p);
				perfil = new Perfil(0, name, email, password, p);
				buscarPerfil.insertar(perfil);
				request.setAttribute("messageSuccess", "Registro Exitoso!!");
				return "/login.jsp";

			} else {

				request.setAttribute("messageError", "Contraseña No Coinciden.");
				request.getRequestDispatcher("/registro_user.jsp").forward(request, response);
			}

		}
		return null;
	}

}
