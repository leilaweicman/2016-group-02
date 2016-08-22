package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.DatosBusquedaRepository;
import grupo2.tpAnual.DatosDeBusqueda;

public class PorTerminalOUsuario {
	DatosBusquedaRepository repositorio;

	public PorTerminalOUsuario(DatosBusquedaRepository datosDeBusquedaRepository) {
		this.repositorio = datosDeBusquedaRepository;

	}

	public List<Integer> obtenerReportePorTerminal(String nombreTerminal) {
		List<Integer> reporte = new ArrayList<Integer>();
		reporte.addAll(repositorio.obtenerPorNombre(nombreTerminal).map(registro -> registro.getTotalDeResultados())
				.collect(Collectors.toList()));
		return reporte;
	}

	public Map<String, Integer> obtenerReportePorUsuario() {
		Map<String, Integer> reporte = new HashMap<String, Integer>();
		for (DatosDeBusqueda registro : this.repositorio.consultarDatos()) {
			reporte.put(registro.getNombre(),
					this.obtenerReportePorTerminal(registro.getNombre()).stream().reduce(0, (a, b) -> a + b));
		}
		return reporte;
	}
}
