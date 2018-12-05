package com.inventario.dao.imp;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.inventario.bo.Producto;
import com.inventario.con.DataBaseException;
import com.inventario.con.HibernateHelper;
import com.inventario.dao.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public void insertar(Producto producto) throws DataBaseException {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.save(producto);
		session.getTransaction().commit();

	}

	@Override
	public void salvar(Producto producto) throws DataBaseException {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.saveOrUpdate(producto);
		session.getTransaction().commit();

	}

	@Override
	public void borrar(Producto producto) throws DataBaseException {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		session.beginTransaction();
		session.delete(producto);
		session.getTransaction().commit();

	}

	@Override
	public List<Producto> buscarTodos() throws DataBaseException {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();

		@SuppressWarnings("unchecked")
		List<Producto> producto = session.createQuery("From Producto producto").list();

		return producto;

	}

	@Override
	public Producto buscarPorClave(String id) throws DataBaseException {

		SessionFactory factoria = HibernateHelper.getSessionFactory();
		Session session = factoria.openSession();
		Query consulta = session
				.createQuery("SELECT producto FROM Producto producto WHERE producto.persona.cedula=:id");
		consulta.setString("id", id);

		List<Producto> p = (List<Producto>) consulta.list();

		Producto producto = null;

		for (Producto produc : p) {

			producto = new Producto(produc.getNombre(), produc.getEstatus(), produc.getDescripcion(),
					produc.getPersona());
		}

		return producto;

	}

}
