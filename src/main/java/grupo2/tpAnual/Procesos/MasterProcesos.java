package grupo2.tpAnual.Procesos;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MasterProcesos {
	private List<Proceso> procesos;
	private LogEjecucionProcesos log;
	private Timer timer;

	
	public MasterProcesos(){
	    this.timer = new Timer();
		this.log = new LogEjecucionProcesos();
	}
	
	//validar que no haya dos para la misma fecha y horario
	public void agregarProceso(Proceso proceso){
		this.procesos.add(proceso);
		 
	}
	
	public void ejecutarProceso(Proceso proceso){
		//validar como hacer para que ejecute lo q tiene q ejecutar en horario
		boolean estadoEjecucion = proceso.ejecutarProceso();
		if(!estadoEjecucion){ //si es true,funciona bien y no quiero que haga nada, en caso de fallo quiero que de true el if para que ejecute
			proceso.ejecutarProcedimientoAnteFallo();
		}
		this.log.loguearProceso(new DatosParaLogEjecucionProcesos(proceso.getFechaEjecucion(), proceso.getHoraEjecucion(),estadoEjecucion,5));
		
	}
	
}
