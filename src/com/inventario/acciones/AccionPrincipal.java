package com.inventario.acciones;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.inventario.bo.Perfil;
import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.PerfilDAO;
import com.inventario.dao.PersonaDAO;
import com.inventario.dao.ProductoDAO;
import com.inventario.servicio.ServicioPerfil;
import com.inventario.servicio.ServicioPersona;
import com.inventario.servicio.ServicioProducto;
import com.inventario.servicio.impl.ServicioPerfilImpl;
import com.inventario.servicio.impl.ServicioPersonaImpl;
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
	public String getLoggin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();

		// Ver la differencia.
		session.invalidate();
		// session.removeAttribute("sessionUsuario");
		System.out.println("Eliminando Sessión " + session.getId());
		// Lo realizo de esta manera, debido a que cuando lo reidirigo a Principal.do
		// me crea un nueva sessión temporal y no elimina la variable sessión, de esta
		// manera si.
		return "/index.jsp";

	}

	/**
	 * Creando Spring a nivel Web Y nos aseguramos que Spring cargue el fichero de
	 * configuración una única vez.
	 * 
	 * @param nombre
	 * @param request
	 * @return
	 */
	public Object getBean(String nombre, HttpServletRequest request) {

		WebApplicationContext factoria = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession().getServletContext());
		return factoria.getBean(nombre);

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

		// System.out.println("Sessión " + session.getId());
		// if (session.getAttribute("sessionUsuario") != null)
		if (session.getId() != null) {

			ServicioPersona servicio = (ServicioPersona) getBean("servicioPersonaImpl", request);

			int pagActual;
			final int perReg = 5;

			if (request.getParameter("pagActual") == null) {

				pagActual = 1;

			} else {

				pagActual = Integer.valueOf(request.getParameter("pagActual"));

			}

			List<Producto> perPagination = servicio.getPerPagination(pagActual, perReg);

			// N° de filas de nuetra tabla.
			Long rows = servicio.getRows();

			int nPages = (int) Math.ceil((double) rows / (double) perReg);

			request.setAttribute("nPages", nPages);
			request.setAttribute("pagActual", pagActual);
			request.setAttribute("perPage", perReg);
			request.setAttribute("Lista_Productos", perPagination);

			return "principal/principal.jsp";

		} else {

			System.out.println("Sessión " + session.getId());
			return "/Principal.do";

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

		ServicioPersona persona = (ServicioPersona) getBean("servicioPersonaImpl", request);
		ServicioProducto product = (ServicioProducto) getBean("servicioProductoImpl", request);

		Persona per = new Persona(request.getParameter("cedula"), request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("telefono"));

		// Mejorar, esto recibirá varios productos pendiente con la Colección
		// por lo momentos lo haré así. Tomar en cuenta que el constructor recibe una
		// colección
		// o sea, varios productos, buscar la manera de recibir esas cantidades.

		Producto pro = new Producto(request.getParameter("producto"), request.getParameter("status"),
				request.getParameter("descripcion"), per);

		// Se inyecta el ID del producto
		pro.setId(Integer.parseInt(request.getParameter("id_producto")));
		List<Producto> productos = new ArrayList<Producto>();

		productos.add(pro);
		per.setProductos(productos);

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

		ServicioPersona persona = (ServicioPersona) getBean("servicioPersonaImpl", request);

		persona.borrar(persona.buscarPorClave(request.getParameter("cedula")));

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
			ServicioPersona persona = (ServicioPersona) getBean("servicioPersonaImpl", request);

			Persona encontrada = persona.buscarPorClave(cedula);
			request.setAttribute("encontrada", encontrada);
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

		ServicioPersona servicioInsertar = (ServicioPersona) getBean("servicioPersonaImpl", request);
		ServicioProducto servicioProduct = (ServicioProducto) getBean("servicioProductoImpl", request);

		Persona persona = new Persona(request.getParameter("cedula"), request.getParameter("nombre"),
				request.getParameter("apellido"), request.getParameter("telefono"));

		servicioInsertar.insertar(persona);

		servicioProduct.insertar(new Producto(request.getParameter("producto"), request.getParameter("status"),
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

		ServicioPerfil buscarPerfil = (ServicioPerfil) getBean("servicioPerfilImpl", request);
		ServicioPersona insertarPersona = (ServicioPersona) getBean("servicioPersonaImpl", request);

		Perfil perfil = buscarPerfil.buscarPorClave(email);
		System.out.println(" " + perfil + " Estoy ");

		if (perfil != null) {

			System.out.println("Contraseña No Coinciden.");
			request.setAttribute("messageError", "Perfil Existe.");
			return "/registro_user.jsp";

		} else {

			System.out.println("perfil no existe");

			if (password.equalsIgnoreCase(pass)) {

				// Cédula de persona debe ser validada, por los momentos lo dejaré así para
				// realizar pruebas.
				Persona p = new Persona(request.getParameter("cedula"), request.getParameter("nombre"),
						request.getParameter("apellido"), request.getParameter("telefono"));

				insertarPersona.insertar(p);
				perfil = new Perfil(name, email, password, p);
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
