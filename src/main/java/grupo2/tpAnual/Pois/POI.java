package grupo2.tpAnual.Pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.ConverterPoint;
import grupo2.tpAnual.Direccion;
import grupo2.tpAnual.AccesoriosPois.Comuna;

@org.mongodb.morphia.annotations.Entity
@Entity 
@Table(name="POI") 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class POI {

	@Id	@GeneratedValue
	private Integer id;
	
	//@Column(name="id_direccion") @OneToOne @JoinColumn(name="id_direccion")
	//@Cascade(value=CascadeType.ALL)
	@Transient
	private Direccion direccion;
	
	@Transient //@Column(name="id_comuna")@ManyToOne @JoinColumn(name="id_comuna")
	protected Comuna comuna;
	
	@ElementCollection
	private List<String> palabraClave;

	@Transient //@Column(name="ubicacion") @Convert(converter = ConverterPoint.class)
	protected Point ubicacion;

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