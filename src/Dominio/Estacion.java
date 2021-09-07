package Dominio;

import java.sql.Time;

import Ventanas.EstadoEstacion;

public class Estacion {

		private Integer id;
		private String nombre;
		private String apertura;
		private String cierre;
		private EstadoEstacion estado;
		
		public Estacion() {}
		 
		public Estacion(Integer id, String nom, String ap, String cie){
			this.id = id;
			this.nombre = nom;
			this.apertura = ap;
			this.cierre = cie;
			this.estado = EstadoEstacion.Operativa;
		}
		
		public Estacion(Integer id, String nom, String ap, String cie, String ee){
			this.id = id;
			this.nombre = nom;
			this.apertura = ap;
			this.cierre = cie;
			if(ee.equals("Operativa")) this.estado = EstadoEstacion.Operativa;	
			else this.estado = EstadoEstacion.Mantenimiento;
			}
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApertura() {
			return apertura;
		}
		public void setApertura(String apertura) {
			this.apertura = apertura;
		}
		public String getCierre() {
			return cierre;
		}
		public void setCierre(String cierre) {
			this.cierre = cierre;
		}
		public EstadoEstacion getEstado() {
			return estado;
		}
		public void setEstado(EstadoEstacion estado) {
			this.estado = estado;
		}
		
		@Override
		public String toString() {
			return id+" "+nombre+" "+apertura+" "+cierre+" "+estado;
		}
		
	}
