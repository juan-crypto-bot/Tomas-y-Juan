package Dominio;

import Logica.RutaService;

public class App {
 
	public static void main(String[] args) {
		Grafo g1 = new Grafo();
		//g1.funcion();
		//System.out.println(g1.funcionRuta().toString());
		RutaService rs = new RutaService();
		Graph gr1 = new Graph();
		g1.funcionEstacion().forEach(estacion -> gr1.addNodo(estacion.getValue()));
		rs.rutas().forEach(ruta -> gr1.conectar(ruta.getOrigin(), ruta.getEnd(), ruta.getIdRuta(), ruta.getDistancia(), ruta.getDuracion(), ruta.getCapacidad(), ruta.getEstado().toString(), ruta.getCosto()));
		System.out.println(gr1.getLista());
		System.out.println(gr1.getR());
	}
}
