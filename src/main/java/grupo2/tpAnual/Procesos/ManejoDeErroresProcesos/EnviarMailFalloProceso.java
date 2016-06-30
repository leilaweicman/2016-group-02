package grupo2.tpAnual.Procesos.ManejoDeErroresProcesos;

import grupo2.tpAnual.MailSender;

public class EnviarMailFalloProceso implements AccionEnCasoDeFallo{
	
	private MailSender mailSender;
	private String mailAdministrador;
	
	@Override
	public void ejecutarConfiguracionPorFallo() {
		mailSender.send(mailAdministrador, "Fallo al ejecutar un proceso", "Se ha producido un error al ejecutar el proceso");
	}
	
	

}
