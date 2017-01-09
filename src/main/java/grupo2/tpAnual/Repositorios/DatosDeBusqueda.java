package grupo2.tpAnual.Repositorios;


import org.bson.types.ObjectId;

import grupo2.tpAnual.LocalDateConverter.LocalDateConverter;
import grupo2.tpAnual.Pois.POI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.mongodb.morphia.annotations.Entity
@org.mongodb.morphia.annotations.Converters(LocalDateConverter.class)

public class DatosDeBusqueda {
	@org.mongodb.morphia.annotations.Id
	private ObjectId id;
	@org.mongodb.morphia.annotations.Property
	private String txtBuscado;
	@org.mongodb.morphia.annotations.Property
	private long segundosQueTardoLaBusqueda;
	@org.mongodb.morphia.annotations.Property
	private int totalDeResultados;		
	@org.mongodb.morphia.annotations.Property 	
	private LocalDate fecha;
	@org.mongodb.morphia.annotations.Property
	private String nombreTerminal;
	@org.mongodb.morphia.annotations.Embedded
	private List<POI> poisRespuestaBusqueda;
	@org.mongodb.morphia.annotations.Property
	private Integer cantidadDePois;
	
	public DatosDeBusqueda(String nombre, String texto, long segundos, int totalResultados, LocalDate fecha,List<POI> poisRespuesta ) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.fecha = fecha;
		this.nombreTerminal = nombre;
		this.poisRespuestaBusqueda=new ArrayList<>();
		this.poisRespuestaBusqueda.addAll(poisRespuesta);
		this.cantidadDePois = this.poisRespuestaBusqueda.size();
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
	public Integer getCantidadDePois(){
		return cantidadDePois;
	}
	
	public Boolean esLaTerminal(String terminal){
		return this.getNombre().equals(terminal);
	}
	
	public Boolean tieneEstaCantidadDePois(Integer cantidad){
		return this.getCantidadDePois() == cantidad;
	}
	
	public Boolean estaEntreFechas(LocalDate fechaDesde, LocalDate fechaHasta){
		
		if (fechaDesde == null || fechaHasta == null) {
			return false;
		}
		return (this.fecha.isAfter(fechaDesde) && this.fecha.isBefore(fechaHasta));
		
	}
	
	public Boolean fechaMayorOIgualA(LocalDate fecha){
		
		if (fecha == null) {
			return false;
		}
		return (this.fecha.isAfter(fecha) || this.fecha.isEqual(fecha) );
		
	}
	public Boolean fechaMenorOIgualA(LocalDate fecha){
		
		if (fecha == null) {
			return false;
		}
		return (this.fecha.isBefore(fecha) || this.fecha.isEqual(fecha));
		
	}
}
