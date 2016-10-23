package grupo2.tpAnual.Repositorios;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import grupo2.tpAnual.LocalDateConverter.LocalDateConverter;
import grupo2.tpAnual.Pois.POI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.*;

@Entity
@Converters(LocalDateConverter.class)

public class DatosDeBusqueda {
	@Id
	private ObjectId id;
	@Property
	private String txtBuscado;
	@Property
	private long segundosQueTardoLaBusqueda;
	@Property
	private int totalDeResultados;		
	@Property //@Convert(LocalDateConverter.class)	
	private LocalDate fecha;
	@Property
	private String nombreTerminal;
	@Embedded
	private List<POI> poisRespuestaBusqueda;

	public DatosDeBusqueda(String nombre, String texto, long segundos, int totalResultados, LocalDate fecha,List<POI> poisRespuesta ) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.fecha = fecha;
		this.nombreTerminal = nombre;
		this.poisRespuestaBusqueda=new ArrayList<>();
		this.poisRespuestaBusqueda.addAll(poisRespuesta);
	}
	
	public DatosDeBusqueda() {
		
	}
	
	
	public String getTxtBuscado() {
		return txtBuscado;
	}

	public long getSegundosQueTardoLaBusqueda() {
		return segundosQueTardoLaBusqueda;
	}

	public int getTotalDeResultados() {
		return totalDeResultados;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getNombre() {
		return nombreTerminal;
	}
	 public List<POI> getPoisRespuestaBusqueda() {
		 return poisRespuestaBusqueda;
	 }
}
