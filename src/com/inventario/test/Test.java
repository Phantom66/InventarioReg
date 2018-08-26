package com.inventario.test;

import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.imp.PersonaDAOImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Producto [] p = {new Producto(123456789, "Mesa", "Buen estado", "descripcion"), new Producto(852369741, "Computadora", "Buen estado", "descripcion")};
		Persona persona = new Persona(20044824, 04256366, "Williams", "Leotur");	
		
		PersonaDAOImpl perDao = new PersonaDAOImpl();
		
		//perDao.insertar(persona);
		
		
		
		List<Persona>per = perDao.buscarTodos();;
		for( Persona e: per) {
			
			System.out.println(e.getNombre() + " "+e.getApellido());
		}
	}

}
