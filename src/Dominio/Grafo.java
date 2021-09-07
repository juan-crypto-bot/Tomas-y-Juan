package Dominio;

import java.util.ArrayList;
import java.util.List;

import Logica.EstacionesService;
import Logica.RutaService;

public class Grafo {

	EstacionesService es = new EstacionesService();
	RutaService rs = new RutaService();
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Ruta> rutas = new ArrayList<Ruta>();
	
	public List<Vertex> funcionEstacion() {
	
		for(int i=0; i<es.estaciones().size(); i++) {
			Vertex<Estacion> v = new Vertex<Estacion>(es.estaciones().get(i));
			vertices.add(v);
		}
		return vertices;
	}
	
	public List<Ruta> funcionRuta(){
		for(int i=0; i<rs.rutas().size(); i++) {
			Ruta r = new Ruta(rs.rutas().get(i).getOrigin(), rs.rutas().get(i).getEnd(), rs.rutas().get(i).getIdRuta(), rs.rutas().get(i).getDistancia(),
					rs.rutas().get(i).getDuracion(), rs.rutas().get(i).getCapacidad(), rs.rutas().get(i).getEstado(), rs.rutas().get(i).getCosto()); 
			rutas.add(r);
		}
		return rutas;
	}
}
