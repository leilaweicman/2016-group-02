package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.*;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

@Entity
@Table(name="POI")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class POI {

	@Id
	@GeneratedValue
	@Column(name="poi_id")
	private Integer id;
	
	private Direccion direccion;
	private List<String> palabraClave;
	protected Point ubicacion;
	protected Comuna comuna;
	private String nombre;
	
	public POI(String nombre, Point ubicacion) {
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.palabraClave = new ArrayList<String>();
	}

	public void setId(Integer numeroVerificador) {
		this.id = numeroVerificador;
	}

	public Integer getId() {
		return this.id;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion dir) {
		this.direccion = dir;
	}

	public void addPalabraClave(String pc) {
		this.palabraClave.add(pc);
	}

	public void setPalabrasClaves(List<String> palabras) {
		this.palabraClave = palabras;
	}

	public List<String> getPalabraClave() {
		return palabraClave;
	}

	abstract boolean estaDisponible(DateTime momento, String nombreServicio);

	public boolean verificaPorTexto(String texto) {
		return getPalabraClave().contains(texto) || tieneTextoEnOtrosAtributos(texto);
	}

	public boolean tieneTextoEnOtrosAtributos(String texto) {
		return false;
	};

	public void setUbicacion(double latitud, double longitud) {
		this.ubicacion = Point.and(latitud, longitud);
	}

	abstract boolean estaCerca(Point coordenadaDeseada);

	public Point getUbicacion() {
		return ubicacion;
	}

	
	public void setComuna(Comuna com) {
		this.comuna = com;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}