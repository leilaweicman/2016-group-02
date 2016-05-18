package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Servicio {
	private String nombre;
	
	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();
		
	private Disponibilidad disponibilidad;
	
	//agrego constructor para que no falle POI test
	public Servicio(){
		
	}
	
	public Servicio(List<Rango> rango){
		this.rangoDisponibilidad = rango;
		disponibilidad = new Disponibilidad(this.rangoDisponibilidad);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Nom) {
		this.nombre = Nom;
	}

	public List<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(List<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}

	public boolean estaDisponible(DateTime momento) 
	{
		return this.disponibilidad.estaDisponible(momento);
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}