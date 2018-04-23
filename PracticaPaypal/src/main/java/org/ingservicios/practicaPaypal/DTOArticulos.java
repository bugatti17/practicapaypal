package org.ingservicios.practicaPaypal;

public class DTOArticulos {
	//Datos de nuestra tabla
		private String nombre;
		private float precio;
		private String codigo;
		
		
		public DTOArticulos() {
			nombre="";
			precio=0;
			codigo="";
		}
		
		public DTOArticulos(String nombre2, float precio2, String codigo2) {
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
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		
		
}