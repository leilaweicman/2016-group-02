package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class POI {

	private Direccion direccion;
	@JsonProperty("servicios")
	private List<String> palabraClave;
	protected Point ubicacion;
	protected Comuna comuna;
	@JsonProperty("id")
	private Integer id;

	public POI() {
		palabraClave = new ArrayList<String>();
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

	abstract boolean estaCerca(Point coordenadaDeseada);

	public Point getUbicacion() {
		return ubicacion;
	}

	public void setComuna(Comuna com) {
		this.comuna = com;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public List<POI> busqueda(String txtABuscar) {
		List<POI> resultadoBusqueda = new ArrayList<POI>();
		if (this.verificaPorTexto(txtABuscar)) {
			resultadoBusqueda.add(this);
		}
		return resultadoBusqueda;
	}
}