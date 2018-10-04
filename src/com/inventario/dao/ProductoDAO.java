package com.inventario.dao;

import java.util.List;
import com.inventario.bo.Producto;


public interface ProductoDAO {
	
	public abstract void insertar(Producto producto, int id);
	public abstract void salvar(Producto producto);
	public abstract void borrar(Producto producto);
	public List<Producto>buscarTodos();
	public abstract Producto buscarPorClave(String id);

}
