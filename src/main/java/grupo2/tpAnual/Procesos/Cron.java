package grupo2.tpAnual.Procesos;

import java.util.List;
import java.util.Timer;

public class Cron {
	private List<Proceso> procesos;
	private Timer timer;
	
	public Cron(){
	    this.timer = new Timer();
	}
	
	public void agregarProceso(Proceso proceso){		
		this.procesos.add(proceso);		
	}
	
	public void ejecutarProcesos(Proceso proceso){
		proceso.ejecutarProceso(); 
	}
}
