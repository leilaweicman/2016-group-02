package grupo2.tpAnual;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public class Banco extends POI {

	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();

	private List<Integer> dias = new ArrayList<Integer>();
	private Disponibilidad disponibilidad;

	// constructor
	public Banco() {
		dias = Arrays.asList(1, 2, 3, 4, 5);
		dias.stream().forEach(dia -> crearRango(dia));
		this.disponibilidad = new Disponibilidad(rangoDisponibilidad);
	}

	private void crearRango(int dia) {
		Rango unRango = new Rango();
		unRango.setDia(dia);
		unRango.setHoraDesde(LocalTime.of(10, 0, 0));
		unRango.setHoraHasta(LocalTime.of(15, 0, 0));
		rangoDisponibilidad.add(unRango);
	}

	public List<Rango> getRango() {
		return rangoDisponibilidad;
	}

	public void setRango(ArrayList<Rango> rango) {
		this.rangoDisponibilidad = rango;
	}

	public void addRango(Rango rango) {
		this.rangoDisponibilidad.add(rango);
	}

	public boolean estaDisponible(DateTime momento, String nombre) {
		return this.disponibilidad.estaDisponible(momento);
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public boolean estaCerca(Point coordenadaDeseada) {
		return (this.ubicacion.distance(coordenadaDeseada) < 0.5);
	}

}