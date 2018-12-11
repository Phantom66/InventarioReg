package com.inventario.servicio;

import java.util.List;

import com.inventario.bo.Producto;

public interface ServicioProducto {
	
	public abstract void insertar(Producto producto);
	public abstract void salvar(Producto producto);
	public abstract void borrar(Producto producto);
	public List<Producto>buscarTodos();
	public abstract Producto buscarPorClave(String id);

}
