package grupo2.tpAnual.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="Servicio") 
public class Servicio {
	@Id	@GeneratedValue @Column(name="id_rango")
	private Integer id;
	@Column(name="nombre")
	private String nombre;

	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();

	private Disponibilidad disponibilidad;

	public Servicio(List<Rango> rango) {
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

	public boolean estaDisponible(DateTime momento) {
		return this.disponibilidad.estaDisponible(momento);
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}