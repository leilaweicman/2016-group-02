package grupo2.tpAnual;


import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Observers.NotificarDatosBusqueda;

public class RegistrarBusquedaTest {
	NotificarDatosBusqueda observer = new NotificarDatosBusqueda();
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras","libros", 18, 10, 15, new LocalDate());

	@Test
	public void notificarBusquedaTest() {
	/*	this.observer.notificarBusqueda(datoBuscado);
		Assert.assertEquals(this.observer.getRegistroBusqueda().size(), 1);*/

	}
}
