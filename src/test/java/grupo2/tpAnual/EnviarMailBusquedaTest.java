package grupo2.tpAnual;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.joda.time.LocalDate;
import org.junit.Test;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;

public class EnviarMailBusquedaTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado2 = new DatosDeBusqueda("flores", "asado", 10, 15, new LocalDate());

	@Test
	public void enviarMailTest() {
		EnviarMailBusqueda observer = new EnviarMailBusqueda(1);
		System.setOut(new PrintStream(outContent));
		observer.notificarBusqueda(datoBuscado);
		assertEquals("Se envio el mail correctamente", outContent.toString());
	}

	@Test
	public void noEnviarMailTest() {
		EnviarMailBusqueda observer = new EnviarMailBusqueda(18);
		System.setOut(new PrintStream(outContent));
		observer.notificarBusqueda(datoBuscado2);
		assertEquals("La busqueda se ejecuto correctamente", outContent.toString());
	}
}
