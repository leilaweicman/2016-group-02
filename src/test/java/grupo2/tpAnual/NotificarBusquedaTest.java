package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Observers.NotificarDatosBusqueda;

public class NotificarBusquedaTest {
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("corrientes", "peras", 10, 15, new LocalDate());
	NotificarDatosBusqueda observer = new NotificarDatosBusqueda();

	@Test
	public void notificarDatosDeBusquedaTest() {
		this.observer.notificarBusqueda(datoBuscado);
		Assert.assertEquals(this.observer.getRegister().consultarDatos().contains(datoBuscado), 1);
	}
}
