package grupo2.tpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;

public class OrigenesDeDatosPOIsTest {
	private OrigenesDeDatosPOIs origenesPOI;
	private Comercio kosiuko;
	private Rango unRango;
	private List<Rango> listaRangos;

	@Before
	public void init() {
		unRango = new Rango();
		unRango.setDia(1);
		unRango.setHoraDesde(LocalTime.of(9, 0, 0));
		unRango.setHoraHasta(LocalTime.of(18, 0, 0));
		listaRangos = Arrays.asList(unRango);
		this.kosiuko = new Comercio(listaRangos);

		this.origenesPOI = new OrigenesDeDatosPOIs();

	}

	@Test
	public void testCrearPoiAcierto() {
		this.origenesPOI.agregarPOI(kosiuko);
		Assert.assertEquals(this.origenesPOI.getPOIs().size(), 1);
	}

	/*
	 * @Test public void testBusquedaPorServicio() {
	 * //Assert.assertTrue(rentas.VerificarPorTexto("Jubilados")); }
	 */

}
