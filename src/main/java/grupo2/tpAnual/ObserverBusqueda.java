package grupo2.tpAnual;

public interface ObserverBusqueda {
	//lo implementan RegistrarBusqueda y EnviarMailBusqueda
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver);

}
