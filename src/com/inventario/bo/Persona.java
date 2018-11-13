package com.inventario.bo;

import java.util.Arrays;

public class Persona {
	//private int id;
	private int cedula;
	private String nombre, apellido, telefono;
	
	private Producto [] productos;
	private Perfil [] perfil;

	public Persona() {};
	
	public Persona(int cedula, String nombre, String apellido, String telefono, Producto[] productos) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.productos = productos;
	}


	public Persona(int cedula, String nombre, String apellido, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}

//	
//	public Persona(int id, int cedula, String nombre, String apellido, String telefono, Perfil[] perfil) {
//		this.id = id;
//		this.cedula = cedula;
//		this.nombre = nombre;
//		this.apellido = apellido;
//		this.telefono = telefono;
//		this.perfil = perfil;
//	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + cedula;
		//result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + Arrays.hashCode(perfil);
		result = prime * result + Arrays.hashCode(productos);
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (cedula != other.cedula)
			return false;
		// if (id != other.id)
		// return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (!Arrays.equals(perfil, other.perfil))
			return false;
		if (!Arrays.equals(productos, other.productos))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

//	public int getId() {
//		
//		return id;
//	}
//	
//	public void setId(int id) {
//		
//		this.id = id;
//	}
	
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

	

	public Perfil[] getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil[] perfil) {
		this.perfil = perfil;
	}



	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", productos=" + Arrays.toString(productos) + "]";
	}
	
	


}
