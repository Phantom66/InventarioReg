package com.inventario.test;

import com.inventario.bo.Persona;
import com.inventario.bo.Producto;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Producto [] p = {new Producto(123456789, "Mesa", "Buen estado", "descripcion"), new Producto(852369741, "Computadora", "Buen estado", "descripcion")};
		
		
		Persona persona = new Persona(17639604, 04256366, "Andres", "Leotur", p);
		
		//System.out.println(persona.toString());
		
		Persona persona1 = new Persona(17639604, 04256366, "Andres", "Leotur");
		Producto p1 = new Producto(123456789, "Mesa", "Buen estado", "descripcion", new Persona(17639604, 04256366, "Andres", "Leotur"));
		
		System.out.println(p1.toString());
	}

}
