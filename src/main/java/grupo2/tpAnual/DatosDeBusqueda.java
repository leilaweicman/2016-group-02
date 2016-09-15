package grupo2.tpAnual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalDate;
@Entity
public class DatosDeBusqueda {
	@Id @GeneratedValue
	private long id;
	private String txtBuscado;
	private long segundosQueTardoLaBusqueda;
	private int totalDeResultados;
	private LocalDate fecha;
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
