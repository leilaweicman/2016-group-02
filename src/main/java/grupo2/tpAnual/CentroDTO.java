package grupo2.tpAnual;

import java.util.List;

public class CentroDTO /* implements BusquedaAvanzada */ {
	private int numeroComuna;
	private String zonas;
	private String nombreDirector;
	private String domicilio;
	private String telefono;
	private List<Servicio> serviciosDTO;

	public int getNumeroComuna() {
		return numeroComuna;
	}

	public String getZonas() {
		return zonas;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public List<Servicio> getServiciosDTO() {
		return serviciosDTO;
	}

}

// @Override
// public List<POI> busqueda(String texto);
/*
 * mockear busqueda que te devuelve una Lista de CGPs, un objeto de la clase
 * CentroDTO int: número de la comuna (ej: 3) string: zonas que incluye (ej.
 * “Balvanera, San Cristóbal” para la comuna 3) string: nombre del director
 * string: domicilio completo del CGP (ej: Junín 521) string: teléfono del CGP
 * (4375-0644/45) lista de “serviciosDTO”: array de servicios que contiene
 * string nombre del servicio (ej: Atención ciudadana) lista de “rangos servicio
 * DTO”: Array con días de servicio que contiene int: número de día de la semana
 * (ej: 1 = Lunes, 2 = Martes, etc.) int: horario desde (9) int: minutos desde
 * (0) int: horario hasta (18) int: minutos hasta (0)
 */

/*
 * listaDePOi=listaDeCGP.forEach(cgp->cgp.transformarENPoi);
 * 
 * return listaDePOi;
 */
