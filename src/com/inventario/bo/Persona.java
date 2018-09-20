package com.inventario.bo;

import java.util.Arrays;

public class Persona {
	private int id;
	private int cedula;
	private String nombre, apellido, telefono;
	
	private Producto [] productos;

	
	public Persona(int cedula, String nombre, String apellido, String telefono, Producto[] productos) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.productos = productos;
	}

	

	public Persona(int cedula, String nombre, String apellido, String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}


	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public int getCedula() {
		return cedula;
	}


	public void setCedula(int cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Producto[] getProductos() {
		return productos;
	}


	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}


	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", productos=" + Arrays.toString(productos) + "]";
	}
	
	


}
