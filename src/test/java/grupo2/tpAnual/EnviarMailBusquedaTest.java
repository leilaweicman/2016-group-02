package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.mockito.Mockito;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;

public class EnviarMailBusquedaTest {
	MailSender mockmail = Mockito.mock(MailSender.class);
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, new LocalDate());

	@Test
	public void enviarMailTest() {
		EnviarMailBusqueda observer = new EnviarMailBusqueda(1, mockmail, "juan@gmail.com");
		observer.notificarBusqueda(datoBuscado);
		Mockito.doThrow(new RuntimeException()).when(mockmail).send("juan@gmail.com", "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
		Mockito.verify(mockmail).send("juan@gmail.com", "Notificacion de Busqueda", "La busqueda tardo mas de lo esperado");
			}
}
