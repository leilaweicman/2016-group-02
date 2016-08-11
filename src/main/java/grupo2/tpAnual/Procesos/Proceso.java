package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.Procesos.ManejoDeErroresProcesos.AccionEnCasoDeFallo;

public abstract class Proceso {
	private int horaEjecucion;
	private LocalDate fechaEjecucion;
	protected OrigenesDeDatosPOIs origenesDeDatos;
	protected List<AccionEnCasoDeFallo> configuracionesFallo = new ArrayList<AccionEnCasoDeFallo>();
	public boolean ejecucionExitosa;
	public LogProcesosRepository log;
	public Criterio criterio;
	public Comuna deComuna;
	public Integer cantidadElementosAfectados;

	public List<AccionEnCasoDeFallo> getConfiguracionesFallo() {
		return configuracionesFallo;
	}

	public void setConfiguracionesFallo(AccionEnCasoDeFallo configuracionFallo) {
		this.configuracionesFallo.add(configuracionFallo);
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public Comuna getDeComuna() {
		return deComuna;
	}

	public void setDeComuna(Comuna deComuna) {
		this.deComuna = deComuna;
	}

	public Proceso(int hora, LocalDate fecha, List<AccionEnCasoDeFallo> configuraciones,
			OrigenesDeDatosPOIs origenesDeDatos) {
		this.horaEjecucion = hora;
		this.configuracionesFallo.addAll(configuraciones);
		this.fechaEjecucion = fecha;
		this.origenesDeDatos = origenesDeDatos;
		this.log = new LogProcesosRepository();
	}

	public void ejecutarProceso(){
		this.cantidadElementosAfectados = 0;
		try {
			ejecutar();
			this.setEstadoProceso(true);
		} catch (Exception e) {
			this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));
			this.setEstadoProceso(false);
		}
		this.log.loguearProceso(new DatosParaLogEjecucionProcesos(this.getFechaEjecucion(), this.getHoraEjecucion(),
				this.ejecucionExitosa, cantidadElementosAfectados));
	}

	
	public abstract void ejecutar();

	public LogProcesosRepository getLog() {
		return this.log;
	}

	public boolean getEjecucionExitosa() {
		return this.ejecucionExitosa;
	}

	public void setEstadoProceso(boolean estado) {
		this.ejecucionExitosa = estado;
	}

	public int getHoraEjecucion() {
		return this.horaEjecucion;
	}

	public LocalDate getFechaEjecucion() {
		return this.fechaEjecucion;
	}

	public void ejecutarProcedimientoAnteFallo() {
		this.configuracionesFallo.forEach(configuracion -> configuracion.ejecutarConfiguracionPorFallo(this));

	}

}
