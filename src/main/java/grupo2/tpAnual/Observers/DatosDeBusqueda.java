package grupo2.tpAnual.Observers;

public class DatosDeBusqueda {
	// Clase que guarda los datos que se quieren almacenar de las busquedas
	private String txtBuscado;
	private long segundosQueTardoLaBusqueda;
	private int totalDeResultados;
	private long tiempoMaximoDeBusqueda;

	public DatosDeBusqueda(String texto, long segundos, long tiempoMaximoDeBusqueda,int totalResultados) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
		this.tiempoMaximoDeBusqueda = tiempoMaximoDeBusqueda;
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

}
