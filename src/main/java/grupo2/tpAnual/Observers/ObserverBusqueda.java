package grupo2.tpAnual.Observers;

import grupo2.tpAnual.DatosDeBusqueda;

public interface ObserverBusqueda {
	// lo implementan RegistrarBusqueda y EnviarMailBusqueda
	public void notificarBusqueda(DatosDeBusqueda datosParaObserver);
}
