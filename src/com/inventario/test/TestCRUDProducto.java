package com.inventario.test;

import com.inventario.bo.Producto;
import com.inventario.dao.imp.ProductoDAOImpl;

/**
 * Clase creada para realizar prueba del CRUD Producto.
 * @author phantom
 *
 */
public class TestCRUDProducto {

	public static void main(String[] args) {
		
		
		Producto producto = new Producto("Mesa","En Buen Estado"," Oficina Principal");
		ProductoDAOImpl proDao = new ProductoDAOImpl();
		
		
		//InsertandoProducto
		//proDao.insertar(producto);
		
		
		//ActualizarProducto
		//proDao.salvar(new Producto(2, "Estante","En Buen Estado"," Oficina Principal"));
		
	}

}
