package grupo2.tpAnual;

import org.joda.time.LocalDate;

public class DatosDeBusqueda {
	// Clase que guarda los datos que se quieren almacenar de las busquedas
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
