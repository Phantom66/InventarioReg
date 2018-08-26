package com.inventario.test;

import java.util.ArrayList;
import java.util.List;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;
import com.inventario.dao.imp.PersonaDAOImpl;

public class TestCRUD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Producto [] p = {new Producto(123456789, "Mesa", "Buen estado", "descripcion"), new Producto(852369741, "Computadora", "Buen estado", "descripcion")};
		Persona persona = new Persona(17639604, "04126315562", "Andrés", "Leotur");	
		
		PersonaDAOImpl perDao = new PersonaDAOImpl();
		
		//Prueba InsertarPersona
		//perDao.insertar(persona);
		
		
		/*Prueba MostrarTodasLasPersona
		List<Persona>per = perDao.buscarTodos();;
		for( Persona e: per) {
			
			System.out.println(e.getNombre() + " "+e.getApellido());
		}
		*/
		
		//Buscar Persona
		//Persona per = perDao.buscarPorClave("17639604");
		//System.out.println(" Nombre de persona encontrada " + per.getNombre() );
		
		//Prueba PersonaActualizada
		//perDao.salvar(new Persona(17639604, "04269089653", "Andres", "Leotur"));
		
		
		//Prueba Persona Eliminada
		//perDao.borrar(persona);
		
		//Persona per = perDao.buscarPorClave("17639604");
		//System.out.println(" Nombre de persona encontrada " + per.getNombre() + " Teléfono: " + per.getTelefono() );
	}

}
