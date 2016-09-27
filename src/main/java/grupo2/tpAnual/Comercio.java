package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

@org.mongodb.morphia.annotations.Entity
@Entity
@Table(name="comercio")
@PrimaryKeyJoinColumn(name="comercio_id", referencedColumnName="poi_id")
public class Comercio extends POI {
	
	@Id
	@GeneratedValue
	@Column(name="comercio_id")
	private Integer id;
	
	@org.mongodb.morphia.annotations.Embedded
	private Rubro rubro;
	private List<Rango> rangoDisponibilidad = new ArrayList<Rango>();
	private Disponibilidad disponibilidad;

	public Comercio(String nombre, Point ubicacion, List<Rango> rango) {
		super(nombre, ubicacion);
		this.rangoDisponibilidad = rango;
		disponibilidad = new Disponibilidad(this.rangoDisponibilidad);
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