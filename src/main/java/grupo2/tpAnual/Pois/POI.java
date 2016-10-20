package grupo2.tpAnual.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.ConverterPoint;
import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.AccesoriosPois.Direccion;

@org.mongodb.morphia.annotations.Entity
@Entity 
@Table(name="POI") 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class POI {

	@org.mongodb.morphia.annotations.Id
	@Id	@GeneratedValue
	private Integer id;
	
	@org.mongodb.morphia.annotations.Reference
	@OneToOne(cascade=CascadeType.ALL) //para que lo hereden todos los hijos
	private Direccion direccion;
	
	@org.mongodb.morphia.annotations.Reference
	@ManyToOne(cascade=CascadeType.ALL)
	protected Comuna comuna;
	
	@org.mongodb.morphia.annotations.Embedded //Morphia
	@ElementCollection
	private List<String> palabraClave;
	
	//@org.mongodb.morphia.annotations.Reference
	@Column @Convert(converter = ConverterPoint.class)
	@Transient
	protected Point ubicacion;
	
	@org.mongodb.morphia.annotations.Property
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

	public abstract boolean estaCerca(Point coordenadaDeseada);

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