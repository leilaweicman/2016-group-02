package grupo2.tpAnual.Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.DatosBusquedaRepository;

public class PorTerminalOUsuario {
	DatosBusquedaRepository register;

	public PorTerminalOUsuario(DatosBusquedaRepository registro) {
		this.register = registro;

	}

	public List<Integer> obtenerReportePorTerminal(String nombreTerminal) {
		List<Integer> reporte = new ArrayList<Integer>();
		reporte.addAll(register.obtenerPorNombre(nombreTerminal).map(registro -> registro.getTotalDeResultados())
				.collect(Collectors.toList()));
		return reporte;
	}

	public Map<String, Integer> obtenerReportePorUsuario() {
		Map<String, Integer> reporte = new HashMap<String, Integer>();
		for (DatosDeBusqueda registro : this.register.consultarDatos()) {
			reporte.put(registro.getNombre(),
					this.obtenerReportePorTerminal(registro.getNombre()).stream().reduce(0, (a, b) -> a + b));
		}
		return reporte;
	}
}
