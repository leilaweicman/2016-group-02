package grupo2.tpAnual.Procesos.ManejoDeErroresProcesos;

public class EnviarMailFalloProceso implements AccionEnCasoDeFallo{

	@Override
	public void ejecutarConfiguracionPorFallo() {
		// enviar mail
		System.out.print("Se envio el mail correctamente");
	}
	
	

}
