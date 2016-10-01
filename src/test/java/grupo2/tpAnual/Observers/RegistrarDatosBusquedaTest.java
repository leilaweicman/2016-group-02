package grupo2.tpAnual.Observers;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepository;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class RegistrarDatosBusquedaTest {
	DatosBusquedaRepository registro = new DatosBusquedaRepository();
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate());

	@Test
	public void agregarDatosBusquedaTest() {
		this.registro.agregarDatosBusqueda(datoBuscado);
		Assert.assertEquals(this.registro.consultarDatos().size(), 1);

	}

}
