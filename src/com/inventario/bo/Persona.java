package com.inventario.bo;

import java.util.Arrays;

public class Persona {
	
	private int cedula;
	private String nombre, apellido, telefono;
	private Producto [] producto;
	
	public Persona() {}
	
	public Persona(int cedula, String telefono, String nombre, String apellido, Producto [] producto) {
		this.cedula = cedula;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellido = apellido;
		this.producto = producto;
	}
	
	

	public Persona(int cedula, String telefono, String nombre, String apellido) {
		this.cedula = cedula;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Producto[] getProducto() {
		return producto;
	}

	public void setProducto(Producto[] producto) {
		this.producto = producto;
	}

	
	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", telefono=" + telefono + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", producto=" + Arrays.toString(producto) + "]";
	}
	

	

}
