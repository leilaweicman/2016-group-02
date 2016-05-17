package grupo2.tpAnual;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import java.sql.Time;
import java.util.*;

public class Comercio extends POI {
	private Rubro rubro;
	
	//BORRAR LISTA DE RANGOS
	private ArrayList<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	
	private Disponibilidad disponibilidad;
	
	public ArrayList<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rub) {
		this.rubro = rub;
	}
	
	//BORRAR
	public boolean estaDisponible(DateTime momento, String nombre) 
	{
		return true;
	}
	
	public boolean estaCerca(Point coordenadaDeseada) {
		return (this.ubicacion.distance(coordenadaDeseada) < rubro.getRadioCercania());
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	
}