package grupo2.tpAnual.Observers;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.MailSender;

public class EnviarMailBusqueda implements ObserverBusqueda {
	private long tiempoMaximo;
	private MailSender mailSender;
	private String mailAdministrador;

	public EnviarMailBusqueda(long tiempoMaximoDeEjecucion, MailSender mailsender, String mailAdministrador) {
		this.tiempoMaximo = tiempoMaximoDeEjecucion;
		this.mailSender = mailsender;
		this.mailAdministrador = mailAdministrador;
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		if (datosParaObserver.getSegundosQueTardoLaBusqueda() > this.tiempoMaximo) {
			mailSender.send(mailAdministrador, "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
		}
	}
}
