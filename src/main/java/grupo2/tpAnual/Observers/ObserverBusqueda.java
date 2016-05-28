package grupo2.tpAnual.Observers;

public interface ObserverBusqueda {
	//lo implementan RegistrarBusqueda y EnviarMailBusqueda
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver);

}
