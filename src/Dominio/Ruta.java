package Dominio;

public class Ruta<T> {

	private Vertex<T> origin;
	private Vertex<T> end;
	private Integer idRuta;
	private Double distancia;
	private Integer duracion;
	private Integer capacidad;
	private EstadoRuta estado;
	private Double costo;

	public Integer getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public EstadoRuta getEstado() {
		return estado;
	}

	public void setEstado(EstadoRuta estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Ruta(Vertex<T> ori, Vertex<T> des, Integer id, Double dis, Integer dur, Integer cap, String er, Double cos){
		this.origin = ori;
		this.end = des;
		this.idRuta = id;
		this.distancia = dis;
		this.duracion = dur;
		this.capacidad = cap;
		this.costo = cos;
		if(er.equals("Activa")) this.estado = EstadoRuta.Activa;
		else this.estado = EstadoRuta.Inactiva;
	} 
	
	public Ruta(Vertex<T> ori, Vertex<T> des, Integer id, Double dis, Integer dur, Integer cap, EstadoRuta er, Double cos){
		this.origin = ori;
		this.end = des;
		this.idRuta = id;
		this.distancia = dis;
		this.duracion = dur;
		this.capacidad = cap;
		this.costo = cos;
		this.estado = er;
	} 
	
	public Ruta(Vertex<T> ini,Vertex<T> fin){
		this.origin = ini;
		this.end = fin;
	}

	public Vertex<T> getOrigin() {
		return origin;
	}

	public void setOrigin(Vertex<T> origin) {
		this.origin = origin;
	}

	public Vertex<T> getEnd() {
		return end;
	}

	public void setEnd(Vertex<T> end) {
		this.end = end;
	}

	public Integer getValue() {
		return idRuta;
	}

	public void setValue(Integer value) {
		this.idRuta = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((idRuta == null) ? 0 : idRuta.intValue());
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
		Ruta other = (Ruta) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (idRuta == null) {
			if (other.idRuta != null)
				return false;
		} else if (!idRuta.equals(other.idRuta))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return origin+" "+end+" "+idRuta+" "+distancia+" "+duracion+" "+capacidad+" "+estado+" "+costo;
	}
}
