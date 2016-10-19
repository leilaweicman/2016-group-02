package grupo2.tpAnual.Repositorios;


import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import grupo2.tpAnual.LocalDateAttributeConverter;

import javax.persistence.*;

@Entity
public class DatosDeBusqueda {
	@Id
	private ObjectId id;
	@Property
	private String txtBuscado;
	@Property
	private long segundosQueTardoLaBusqueda;
	@Property
	private int totalDeResultados;
	@Property("fecha") @Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate fecha;
	@Property
	private String nombre;

	public DatosDeBusqueda(String nombre, String texto, long segundos, int totalResultados, LocalDate fecha) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.fecha = fecha;
		this.nombre = nombre;
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
		return nombre;
	}

}
