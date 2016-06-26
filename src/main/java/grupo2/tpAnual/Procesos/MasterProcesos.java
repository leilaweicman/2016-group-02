package grupo2.tpAnual.Procesos;

import java.util.List;

public class MasterProcesos {
	private List<Proceso> procesos;
	private LogEjecucionProcesos log;
	
	public MasterProcesos(){
		this.log = new LogEjecucionProcesos();
		
	}
	//validar que no haya dos para la misma fecha y horario
	public void agregarProceso(Proceso proceso){
		this.procesos.add(proceso);
	}
	
	public void ejecutarProceso(Proceso proceso){
		//validar como hacer para que ejecute lo q tiene q ejecutar en horario
		proceso.ejecutarProceso();
	//	this.log.loguearProceso(new DatosParaLogEjecucionProcesos(proceso.getFecha(), proceso.getHora(),true,5));
		
	}
	
}
