package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

public class LogProcesosRepository {
	private List<DatosParaLogEjecucionProcesos> logDeProcesos;

	public LogProcesosRepository() {
		logDeProcesos = new ArrayList<>();
	}

	public void loguearProceso(DatosParaLogEjecucionProcesos estadoProceso) {
		this.logDeProcesos.add(estadoProceso);
	}

	public List<DatosParaLogEjecucionProcesos> obtenerLog() {
		return this.logDeProcesos;
	}
}
