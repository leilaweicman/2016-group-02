package grupo2.tpAnual.Procesos.ManejoDeErroresProcesos;

import grupo2.tpAnual.Procesos.Proceso;

public class ReintentarEjecucionProceso implements AccionEnCasoDeFallo {
	private int cantidadRepeticiones; 
	public int prioridad=1;
	
	public ReintentarEjecucionProceso(int cantidadRepeticiones){
		this.cantidadRepeticiones = cantidadRepeticiones;
	}

	@Override
	public void ejecutarConfiguracionPorFallo(Proceso proceso) {		
		int i=0;
		while(i<=cantidadRepeticiones && !proceso.getEjecucionExitosa()){
			proceso.ejecutarProceso();
			i++;
		}		
	}
}
