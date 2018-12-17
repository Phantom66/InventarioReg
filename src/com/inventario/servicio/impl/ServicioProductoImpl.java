package com.inventario.servicio.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inventario.bo.Producto;
import com.inventario.dao.ProductoDAO;
import com.inventario.servicio.ServicioProducto;

public class ServicioProductoImpl implements ServicioProducto {
	
	ProductoDAO producto = null;

	public ServicioProductoImpl() {
		
		//DAOFactory factoria =  DAOAbstractFactory.getInstance();
		
		ApplicationContext factoria = new ClassPathXmlApplicationContext("contextoAplicacion.xml");
		
		producto = (ProductoDAO)factoria.getBean("productoDAO");
		
	}

	@Override
	public void insertar(Producto producto) {
		
		this.producto.insertar(producto);
		
	}

	@Override
	public void salvar(Producto producto) {
		
		this.producto.salvar(producto);
	}

	@Override
	public void borrar(Producto producto) {
		

		this.producto.borrar(producto);
		
	}

	@Override
	public List<Producto> buscarTodos() {
		
		return this.producto.buscarTodos();
	}

	@Override
	public Producto buscarPorClave(String id) {
		
		return this.producto.buscarPorClave(id);
	}

}
