package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;

public class Proceso {
	private int horaEjecucion;
	private LocalDate fechaEjecucion; 
	private AccionesDeProcesos accion;
	
	public Proceso(int hora, LocalDate fecha, AccionesDeProcesos accion){
		this.horaEjecucion = hora;
		this.fechaEjecucion = fecha;
		this.accion = accion; 
	}
	
	public void ejecutarProceso(){
		this.accion.ejecutar();
	}
	
	public int getHoraEjecucion(){
		return this.horaEjecucion;
	}
	
	public LocalDate getFechaEjecucion(){
		return this.fechaEjecucion;
	}


}
