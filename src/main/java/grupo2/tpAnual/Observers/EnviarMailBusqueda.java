package grupo2.tpAnual.Observers;

import grupo2.tpAnual.DatosDeBusqueda;
import grupo2.tpAnual.MailSender;

public class EnviarMailBusqueda implements ObserverBusqueda {
	private long tiempoMaximo; 
	private MailSender mailSender;
	private String mailAdministrador;
	
	public EnviarMailBusqueda(long tiempoMaximoDeEjecucion){
		this.tiempoMaximo = tiempoMaximoDeEjecucion;
	}
	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		if (datosParaObserver.getSegundosQueTardoLaBusqueda() > this.tiempoMaximo) {
			mailSender.send(mailAdministrador, "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
		System.out.print("Se envio el mail correctamente");
		}else{
		System.out.print("La busqueda se ejecuto correctamente");
		}
	}

}
