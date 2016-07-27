package grupo2.tpAnual.Procesos.ManejoDeErroresProcesos;

import org.mockito.Mockito;

import grupo2.tpAnual.MailSender;
import grupo2.tpAnual.Procesos.Proceso;

public class EnviarMailFalloProceso implements AccionEnCasoDeFallo{
	public int prioridad=2;
	private MailSender mailSender = Mockito.mock(MailSender.class);
	private String mailAdministrador;
	
	@Override
	public void ejecutarConfiguracionPorFallo(Proceso proceso) {
		if (!proceso.getEjecucionExitosa()){
			mailSender.send(mailAdministrador, "Fallo al ejecutar un proceso", "Se ha producido un error al ejecutar el proceso");
		}
	}
}
