package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;

import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public class Proceso {
	private int horaEjecucion;
	private LocalDate fechaEjecucion; 
	private AccionEnCasoDeFallo configuracionFallo;
	private AccionesDeProcesos accionDeProceso;
	
	public Proceso(int hora, LocalDate fecha, AccionesDeProcesos accion, AccionEnCasoDeFallo configuracion){
		this.horaEjecucion = hora;
		this.configuracionFallo = configuracion;
		this.fechaEjecucion = fecha;
		this.accionDeProceso = accion; 
	}
	
	public boolean ejecutarProceso(){
		return this.accionDeProceso.ejecutar();
	}
	
	
	public int getHoraEjecucion(){
		return this.horaEjecucion;
	}
	
	public LocalDate getFechaEjecucion(){
		return this.fechaEjecucion;
	}

	public void ejecutarProcedimientoAnteFallo() {
		this.configuracionFallo.ejecutarConfiguracionPorFallo();
		
	}

}
