package com.inventario.dao;

import java.sql.SQLException;
import java.util.List;

import com.inventario.bo.Producto;

public interface ProductoDAO {
	
	public abstract void insertar(Producto producto, int id) throws SQLException, ClassNotFoundException;
	public abstract void salvar(Producto producto) throws SQLException, ClassNotFoundException;
	public abstract void borrar(Producto producto)throws SQLException, ClassNotFoundException;
	public List<Producto>buscarTodos() throws SQLException, ClassNotFoundException;
	public abstract Producto buscarPorClave(String id) throws SQLException, ClassNotFoundException;

}
