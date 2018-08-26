package com.inventario.bo;

public class Producto {
	
	private int identificador;
	private String nombre;
	private String estatus;
	private String descripcion;
	Persona persona;
	
	public Producto(){	}
	
	

	public Producto(int identificador, String nombre, String estatus, String descripcion) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
		this.persona = persona;
	}



	public Producto( String nombre, String estatus, String descripcion) {
		//this.identificador = identificador;
		this.nombre = nombre;
		this.estatus = estatus;
		this.descripcion = descripcion;
	}



	public int getIdentificador() {
		return identificador;
	}



	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEstatus() {
		return estatus;
	}



	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	@Override
	public String toString() {
		return "Producto [identificador=" + identificador + ", nombre=" + nombre + ", estatus=" + estatus
				+ ", descripcion=" + descripcion + ", persona=" + persona + "]";
	}
	

	

}
