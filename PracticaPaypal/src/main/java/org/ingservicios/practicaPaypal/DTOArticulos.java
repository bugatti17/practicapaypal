package org.ingservicios.practicaPaypal;


import java.io.Serializable;


public class DTOArticulos implements Serializable{
	//Datos de nuestra tabla
		private String nombre;
		private float precio;
		private int codigo;
		
		
		
		
		public DTOArticulos() {
			nombre="";
			precio=0;
			codigo=0;
		}
		
		public DTOArticulos(String nombre2, float precio2, int codigo2) {
			nombre=nombre2;
			precio=precio2;
			codigo=codigo2;

			
		}
		
		

		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public float getPrecio() {
			return precio;
		}
		public void setPrecio(float precio) {
			this.precio = precio;
		}
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		
		
}