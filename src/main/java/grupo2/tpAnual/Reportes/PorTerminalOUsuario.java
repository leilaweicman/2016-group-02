package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepository;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class PorTerminalOUsuario {
	DatosBusquedaRepository repositorio;

	public PorTerminalOUsuario(DatosBusquedaRepository datosDeBusquedaRepository) {
		this.repositorio = datosDeBusquedaRepository;
	}

	public List<Integer> obtenerReportePorTerminal(String nombreTerminal) {
		return repositorio.obtenerTotalResultadosPorTerminal(nombreTerminal);
	}

	public Map<String, Integer> obtenerReportePorUsuario() {
		Map<String, Integer> reporte = new HashMap<String, Integer>();
		for (DatosDeBusqueda registro : this.repositorio.consultarDatos()) {
			reporte.put(registro.getNombre(), repositorio.cantidadDeBusquedasDe(registro.getNombre()));
		}
		return reporte;
	}
}
