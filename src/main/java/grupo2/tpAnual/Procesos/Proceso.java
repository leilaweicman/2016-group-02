package grupo2.tpAnual.Procesos;

import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public abstract class Proceso {
	private int horaEjecucion;
	private LocalDate fechaEjecucion; 
	protected AccionEnCasoDeFallo configuracionFallo;
	protected OrigenesDeDatosPOIs origenesDeDatos;
	
	public Proceso(int hora, LocalDate fecha, AccionEnCasoDeFallo configuracion, OrigenesDeDatosPOIs origenesDeDatos){
		this.horaEjecucion = hora;
		this.configuracionFallo = configuracion;
		this.fechaEjecucion = fecha;
		this.origenesDeDatos = origenesDeDatos;
	}
	
	public abstract void ejecutarProceso(LogEjecucionProcesos log);
	
	
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
