package grupo2.tpAnual;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import java.util.*;

public class Comercio extends POI {
	private Rubro rubro;
	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	private Disponibilidad disponibilidad;
	private String nombre;

	public Comercio(List<Rango> rango, String nom) {
		nombre = nom;
		this.rangoDisponibilidad = rango;
		disponibilidad = new Disponibilidad(this.rangoDisponibilidad);
	}
	
	public String getNombre(){
		return nombre;
	}

	public List<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(List<Rango> rango) {
		this.rangoDisponibilidad = rango;
		this.disponibilidad.setDisponibilidad(this.rangoDisponibilidad);
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
		this.disponibilidad.addRango(rango);
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rub) {
		this.rubro = rub;
	}

	@Override
	public boolean estaDisponible(DateTime momento, String nombre) {
		return this.disponibilidad.estaDisponible(momento);
	}

	@Override
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