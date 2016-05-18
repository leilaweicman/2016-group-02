package grupo2.tpAnual;

import java.util.ArrayList;
import org.joda.time.DateTime;

public class Servicio {
	private String nombre;
	
	//BORRAR LISTA DE RANGOS
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
		
	private Disponibilidad disponibilidad;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Nom) {
		this.nombre = Nom;
	}

	public ArrayList<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}

	//BORRAR
	public boolean estaDisponible(DateTime momento) 
	{
		return true;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}