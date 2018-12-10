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

	public abstract void salvar(T perfil);

	public abstract void borrar(T perfil);

	public abstract List<T> buscarTodos();

	public abstract T buscarPorClave(Id id);

}
