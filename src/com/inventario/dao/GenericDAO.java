package com.inventario.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaz Generic
 * 
 * @author ALeotur
 *
 * @param <T>
 * @param <Id>
 */
public interface GenericDAO<T, Id extends Serializable> {

	public abstract void salvar(T t);

	public abstract void borrar(T t);

	public abstract List<T> buscarTodos();

	public abstract T buscarPorClave(Id id);

	public abstract void insertar(T t);
}
