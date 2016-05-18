package grupo2.tpAnual;

import java.util.List;


import org.joda.time.DateTime;
import org.uqbar.geodds.Point;

public interface ICGP {
	public void setServicios(List<Servicio> servicios);
	public List<Servicio> getServicios();

	public boolean busquedaParticular(String texto);

	public boolean estaDisponible(DateTime momento, String nombreServicio);

	public boolean estaCerca(Point punto);

	public Direccion getDireccion();

	public void setDireccion(Direccion dir);

	public void addPalabraClave(String pc);

	public List<String> getPalabraClave();

	public boolean VerificarPorTexto(String texto);

	public boolean BusquedaParticular(String texto);

	public void setUbicacion(double latitud, double longitud);

	public Point getUbicacion();

	public void setComuna(Comuna com);

	public List<POI> busqueda(String txtABuscar);

}
