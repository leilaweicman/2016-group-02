package grupo2.tpAnual.Procesos.ManejoDeErroresProcesos;

public class ReintentarEjecucionProceso implements AccionEnCasoDeFallo {
	private int cantidadRepeticiones; 
	
	public ReintentarEjecucionProceso(int cantidadRepeticiones){
		this.cantidadRepeticiones = cantidadRepeticiones; 
	}

	@Override
	public void ejecutarConfiguracionPorFallo() {
		
		
	}
}
