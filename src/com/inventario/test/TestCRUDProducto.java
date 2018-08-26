package com.inventario.test;

import java.util.List;

import com.inventario.bo.Producto;
import com.inventario.dao.imp.ProductoDAOImpl;

/**
 * Clase creada para realizar prueba del CRUD Producto.
 * @author phantom
 *
 */
public class TestCRUDProducto {

	public static void main(String[] args) {
		
		
		Producto producto = new Producto("Estante","En Buen Estado"," Oficina Principal");
		ProductoDAOImpl proDao = new ProductoDAOImpl();
		
		
		//InsertandoProducto
		//proDao.insertar(producto);
		
		Producto productoDos = new Producto(2, "Estante","En Buen Estado"," Oficina Principal");
		//ActualizarProducto
		//proDao.salvar(productoDos);
		
		//Borrar Producto
		//proDao.borrar(productoDos);
		
		/*ListarTodosLosProductos
			List<Producto> p = proDao.buscarTodos();
			
			for (Producto e: p) {
				
				System.out.println("Nombre: " + e.getNombre() + " Estatus: " + e.getEstatus());
				
			}
		*/
		
		//Buscando Producto por Id
		Producto p = proDao.buscarPorClave("6");
		
		System.out.println(" Producot del id: "+p.getIdentificador()  + " Modelo: "+ p.getNombre());
		
	}

}
