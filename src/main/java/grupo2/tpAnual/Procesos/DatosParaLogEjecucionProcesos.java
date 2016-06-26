package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;

public class DatosParaLogEjecucionProcesos {
	private LocalDate fecha;
	private int hora;
	private int cantidadElementosAfectados;
	private boolean ejecucionExitosa;
	public DatosParaLogEjecucionProcesos(LocalDate fecha, int hora, boolean estadoEjecucion, int cantidadElementosAfectados){
		this.fecha = fecha;
		this.cantidadElementosAfectados = cantidadElementosAfectados;
		this.hora = hora;
		this.ejecucionExitosa = estadoEjecucion;
	}
}
