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
	@OneToOne
	private Disponibilidad disponibilidad;

	public Servicio(List<Rango> rango) {
		disponibilidad = new Disponibilidad(rango);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Nom) {
		this.nombre = Nom;
	}

	public List<Rango> getRango() {
		return disponibilidad.getRango();
	}

	public void setRango(List<Rango> rango) {
		this.disponibilidad.setDisponibilidad(rango);
	}

	public void addRango(Rango rango) {
		this.disponibilidad.addRango(rango);
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