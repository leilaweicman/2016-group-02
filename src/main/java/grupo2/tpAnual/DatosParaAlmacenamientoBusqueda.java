package grupo2.tpAnual;

public class DatosParaAlmacenamientoBusqueda {
	// Clase que guarda los datos que se quieren almacenar de las busquedas
	private String txtBuscado;
	private long segundosQueTardoLaBusqueda;
	private int totalDeResultados;

	public DatosParaAlmacenamientoBusqueda(String texto, long segundos, int totalResultados) {
		this.txtBuscado = texto;
		this.segundosQueTardoLaBusqueda = segundos;
		this.totalDeResultados = totalResultados;
	}
}
