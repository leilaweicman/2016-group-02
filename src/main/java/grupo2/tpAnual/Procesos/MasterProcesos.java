package grupo2.tpAnual.Procesos;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.LocalDate;

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
		//hacer para que ejecute lo q tiene q ejecutar en horario
		proceso.ejecutarProceso(this.log); //le paso el log por parametro porque quiero que me quede todo aca
	}
	
}
