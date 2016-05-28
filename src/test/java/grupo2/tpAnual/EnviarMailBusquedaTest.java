package grupo2.tpAnual;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class EnviarMailBusquedaTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	EnviarMailBusqueda observer = new EnviarMailBusqueda();
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("libros", 18, 10, 15);

	@Test
	public void notificarBusquedaTest() { //cuando este implementado hay q cambiar e enviarMailBusqueda por un mockito
		System.setOut(new PrintStream(outContent));
		observer.notificarBusqueda(datoBuscado);
		assertEquals("Se envio el mail correctamente", outContent.toString());
	}
}
