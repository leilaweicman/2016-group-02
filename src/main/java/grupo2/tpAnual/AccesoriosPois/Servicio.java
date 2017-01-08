package grupo2.tpAnual.AccesoriosPois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import grupo2.tpAnual.PersistentEntity;

@Entity
public class Servicio extends PersistentEntity{
	/*@Id	@GeneratedValue 
	private Integer id;*/
	@Column
	private String nombre;
	@OneToMany
	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	@OneToOne
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

	/*public Integer getId() {
		return this.id;
	}*/

}