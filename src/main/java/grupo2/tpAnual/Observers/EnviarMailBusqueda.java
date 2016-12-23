package grupo2.tpAnual.Observers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import grupo2.tpAnual.MailSender;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;


@Entity
public class EnviarMailBusqueda extends ObserverBusqueda {
	
	@Transient
	private long tiempoMaximo;
	@Transient
	private MailSender mailSender;

	private String mailAdministrador;
	
	public EnviarMailBusqueda(){
		
	}

	public EnviarMailBusqueda(long tiempoMaximoDeEjecucion, MailSender mailsender, String mailAdministrador) {
		this.tiempoMaximo = tiempoMaximoDeEjecucion;
		this.mailSender = mailsender;
		this.mailAdministrador = mailAdministrador;
		this.id = 2;
	}

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		if (datosParaObserver.getSegundosQueTardoLaBusqueda() > this.tiempoMaximo) {
			mailSender.send(mailAdministrador, "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
		}
	}
}
