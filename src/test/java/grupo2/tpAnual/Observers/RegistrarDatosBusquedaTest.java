package grupo2.tpAnual.Observers;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class RegistrarDatosBusquedaTest {
	LocalDate today=LocalDate.now();
	DatosBusquedaRepositoryMemory registro = new DatosBusquedaRepositoryMemory();
	DatosDeBusqueda datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, today,new ArrayList<POI>());

	@Test
	public void agregarDatosBusquedaTest() {
		this.registro.agregarDatosBusqueda(datoBuscado);
		Assert.assertEquals(this.registro.consultarDatos().size(), 1);

	}

}
