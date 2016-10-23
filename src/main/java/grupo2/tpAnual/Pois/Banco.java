package grupo2.tpAnual.Pois;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Disponibilidad;
import grupo2.tpAnual.AccesoriosPois.Rango;

@Entity
@DiscriminatorValue("1")
public class Banco extends POI {
	@Transient
	private List<Rango> rangoDisponibilidad;
	/*@Transient
	private List<Integer> dias;*/
	//@Transient
	@OneToOne
	private Disponibilidad disponibilidad;

	public Banco(String nombre, Point ubicacion) {
		super(nombre, ubicacion);
		this.rangoDisponibilidad = new ArrayList<Rango>();
		List<Integer> dias = new ArrayList<Integer>();
		dias = Arrays.asList(1, 2, 3, 4, 5);
		dias.stream().forEach(dia -> crearRango(dia));
		this.disponibilidad = new Disponibilidad(rangoDisponibilidad);
	}

	private void crearRango(int dia) {
		Rango unRango = new Rango(dia, LocalTime.of(10, 0, 0), LocalTime.of(15, 0, 0));
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

	@Override
	public boolean estaDisponible(DateTime momento, String nombre) {
		return this.disponibilidad.estaDisponible(momento);
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public boolean estaCerca(Point coordenadaDeseada) {
		return (this.ubicacion.distance(coordenadaDeseada) < 0.5);
	}

}