package com.inventario.servicio.impl;

import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.dao.DAOFactory;
import com.inventario.dao.ProductoDAO;
import com.inventario.dao.imp.DAOAbstractFactory;
import com.inventario.servicio.ServicioProducto;

public class ServicioProductoImpl implements ServicioProducto {
	
	ProductoDAO producto = null;

	public ServicioProductoImpl() {
		DAOFactory factoria =  DAOAbstractFactory.getInstance();
		producto = factoria.getProductoDAO();
		
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
