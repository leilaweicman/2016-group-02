package grupo2.tpAnual;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.joda.time.LocalDate;
import org.junit.Test;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;

public class EnviarMailBusquedaTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	EnviarMailBusqueda observer = new EnviarMailBusqueda();

	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras","libros", 18, 10, 15, new LocalDate());
	DatosDeBusqueda datoBuscado2 = new DatosDeBusqueda("flores","asado", 5, 10, 15, new LocalDate());
	
	@Test
	public void enviarMailTest() { 
		System.setOut(new PrintStream(outContent));
		observer.notificarBusqueda(datoBuscado);
		assertEquals("Se envio el mail correctamente", outContent.toString());
	}
	
	@Test
	public void noEnviarMailTest(){
		System.setOut(new PrintStream(outContent));
		observer.notificarBusqueda(datoBuscado2);
		assertEquals("La busqueda se ejecuto correctamente", outContent.toString());
	}
}
