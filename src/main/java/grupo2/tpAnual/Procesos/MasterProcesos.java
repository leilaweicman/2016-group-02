package grupo2.tpAnual.Procesos;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.LocalDate;

public class MasterProcesos {
	private List<Proceso> procesos;
	private Timer timer;

	
	public MasterProcesos(){
	    this.timer = new Timer();
	}
	
	//validar que no haya dos para la misma fecha y horario
	public void agregarProceso(Proceso proceso){
		
		this.procesos.add(proceso);
		
	}
	
	public void ejecutarProcesos(Proceso proceso){//no se pasa por parametro, lo tiene que ejecutar desde su lista
		//hacer para que ejecute lo q tiene q ejecutar en horario
		proceso.ejecutarProceso(); 
	}
	
}
