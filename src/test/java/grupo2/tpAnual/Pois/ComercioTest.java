package grupo2.tpAnual.Pois;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.Rango;

public class ComercioTest {
	private Comercio comercio;
	private Rango unRango;
	private Rango otroRango;
	private Rango rango;
	private DateTime momento;
	private List<Rango> listaRangos;

	@Before
	public void init() {
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));

		listaRangos = Arrays.asList(unRango, otroRango, rango);
		comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), listaRangos);
	}

	@Test
	public void estaDisponibleMiercolesALas10() {
		momento = new DateTime("2016-04-20T10:00:00");
		Assert.assertTrue(comercio.estaDisponible(momento, ""));
	}

	@Test
	public void noEstaDisponibleMiercolesALas14() {
		momento = new DateTime("2016-04-20T14:00:00");
		Assert.assertFalse(comercio.estaDisponible(momento, ""));
	}

}