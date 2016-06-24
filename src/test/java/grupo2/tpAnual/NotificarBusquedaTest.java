package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Observers.NotificarDatosBusqueda;

public class NotificarBusquedaTest {
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("corrientes","peras", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado1 = new DatosDeBusqueda("callao","bananas", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado2 = new DatosDeBusqueda("lasHeras","ropa", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado3 = new DatosDeBusqueda("flores","veterinaria", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado4 = new DatosDeBusqueda("caballito","hospital", 10, 15, new LocalDate());
	
	NotificarDatosBusqueda observer = new NotificarDatosBusqueda();
	
	@Test
	public void notificarDatosDeBusquedaTest(){
		this.observer.notificarBusqueda(datoBuscado);
		Assert.assertEquals(this.observer.getRegister().consultarDatos().size(), 1);
	}
	
	@Test
	public void notificarDatosDeBusquedaMuchosDatosTest(){
		this.observer.notificarBusqueda(datoBuscado);
		this.observer.notificarBusqueda(datoBuscado1);
		this.observer.notificarBusqueda(datoBuscado2);
		this.observer.notificarBusqueda(datoBuscado3);
		this.observer.notificarBusqueda(datoBuscado4);
		this.observer.notificarBusqueda(datoBuscado);
		Assert.assertEquals(this.observer.getRegister().consultarDatos().size(), 6);
	}
}
