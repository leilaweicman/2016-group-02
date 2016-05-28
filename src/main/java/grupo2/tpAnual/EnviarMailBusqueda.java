package grupo2.tpAnual;

public class EnviarMailBusqueda implements ObserverBusqueda {

	@Override
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver) {
		if (datosParaObserver.getSegundosQueTardoLaBusqueda() > datosParaObserver.getTiempoMaximoDeBusqueda()) {
			// enviar mail al administrador
		System.out.print("Se envio el mail correctamente");
		}
	}

}
