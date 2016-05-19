package grupo2.tpAnual;

import java.util.List;

public interface CentroDTO {

	public int getNumeroComuna();

	public String getZonas();

	public String getDomicilio();

	public int getTelefono();
	
	public List<Servicio> getServiciosDTO();

	public String getNombreDirector();
	
	public List<POI> busqueda(String texto);
	
}
