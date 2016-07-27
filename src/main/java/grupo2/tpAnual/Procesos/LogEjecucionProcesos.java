package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

public class LogEjecucionProcesos {
	private List<DatosParaLogEjecucionProcesos> logDeProcesos;

	public LogEjecucionProcesos() {
		logDeProcesos = new ArrayList<>();
	}

	public void loguearProceso(DatosParaLogEjecucionProcesos estadoProceso) {
		this.logDeProcesos.add(estadoProceso);
	}

	public List<DatosParaLogEjecucionProcesos> obtenerLog() {
		return this.logDeProcesos;
	}
}
