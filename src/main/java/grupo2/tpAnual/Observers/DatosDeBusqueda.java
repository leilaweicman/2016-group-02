package grupo2.tpAnual.Observers;

import java.util.Date;

public class DatosDeBusqueda {
	// Clase que guarda los datos que se quieren almacenar de las busquedas
	private String txtBuscado;
	private long segundosQueTardoLaBusqueda;
	private int totalDeResultados;
	private long tiempoMaximoDeBusqueda;
	private Date fecha;

	public DatosDeBusqueda(String texto, long segundos, long tiempoMaximoDeBusqueda,int totalResultados, Date fecha) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.tiempoMaximoDeBusqueda = tiempoMaximoDeBusqueda;
		this.fecha = fecha;
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

	public long getTiempoMaximoDeBusqueda() {
		return tiempoMaximoDeBusqueda;
	}
	
	public Date getFecha() {
		return fecha;
	}

}
