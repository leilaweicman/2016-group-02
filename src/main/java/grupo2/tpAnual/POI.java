package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;

public abstract class POI{
	private Direccion direccion;
	private List<String> palabraClave;
	protected Point ubicacion;
	protected Comuna comuna;

	public POI() {

		palabraClave = new ArrayList<String>();
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

	public List<String> getPalabraClave() {
		return palabraClave;
	}

	abstract boolean estaDisponible(DateTime momento, String nombreServicio);

	public boolean verificarPorTexto(String texto) {
		return getPalabraClave().contains(texto) || busquedaParticular(texto);
	}

	// Seteado en false para aquellas implementaciones en las cuales no tengan
	// busqueda particular
	public boolean busquedaParticular(String texto) {
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
		if (this.verificarPorTexto(txtABuscar)) {
			resultadoBusqueda.add(this);
		}
		return resultadoBusqueda;
	}
}