package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.joda.time.LocalDate;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public abstract class Proceso {
	private int horaEjecucion;
	private LocalDate fechaEjecucion; 
	//protected AccionEnCasoDeFallo configuracionFallo;
	protected OrigenesDeDatosPOIs origenesDeDatos;
	protected List<AccionEnCasoDeFallo> configuracionesFallo= new ArrayList<AccionEnCasoDeFallo>();
	public boolean ejecucionExitosa;
	public LogEjecucionProcesos log;
	
	public Proceso(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones, OrigenesDeDatosPOIs origenesDeDatos){
		this.horaEjecucion = hora;
		this.configuracionesFallo.addAll(configuraciones);
		this.fechaEjecucion = fecha;
		this.origenesDeDatos = origenesDeDatos;
		this.log= new LogEjecucionProcesos();
	}
	
	public abstract void ejecutarProceso();
	
	public LogEjecucionProcesos getLog(){
		return this.log;
	}
	
	public boolean getEjecucionExitosa(){
		return this.ejecucionExitosa;
	}
	
	public void setEstadoProceso(boolean estado){
		this.ejecucionExitosa=estado;
	}
	
	public int getHoraEjecucion(){
		return this.horaEjecucion;
	}
	
	public LocalDate getFechaEjecucion(){
		return this.fechaEjecucion;
	}

	public void ejecutarProcedimientoAnteFallo() {		
		this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));

	}
	
	/*public List<AccionEnCasoDeFallo> ordenarLista(){		
		this.configuracionesFallo.sort(elemento1.prioridad>elemento2.prioridad);
	}*/
}
