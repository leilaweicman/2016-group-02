package grupo2.tpAnual;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class RegistrarDatosBusquedaTest {
	RegistrarDatosBusqueda registro = new RegistrarDatosBusqueda();
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());

	@Test
	public void agregarDatosBusquedaTest() {
		this.registro.agregarDatosBusqueda(datoBuscado);
		Assert.assertEquals(this.registro.consultarDatos().size(), 1);

	}

}
