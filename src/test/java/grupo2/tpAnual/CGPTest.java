package grupo2.tpAnual;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class CGPTest {

	private CGP cgp;
	private Servicio unServicio;
	private Servicio otroServicio;
	private Rango unRango;
	private Rango otroRango;
	private DateTime momento;
	private List<Servicio> servicios;
	private List<Rango> rangoUnServicio;
	private List<Rango> rangoOtroServicio;

	@Before
	public void init() {
		cgp = new CGP("comuna caballito", Point.and(-34.664837, -58.385674));
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rangoUnServicio = new ArrayList<Rango>();
		rangoOtroServicio = new ArrayList<Rango>();

		rangoUnServicio.add(unRango);
		rangoOtroServicio.add(otroRango);

		unServicio = new Servicio(rangoUnServicio);
		otroServicio = new Servicio(rangoOtroServicio);

		unServicio.setNombre("Rentas");

		otroServicio.setNombre("otrasRentas");

		servicios = new ArrayList<Servicio>();
		servicios.add(unServicio);
		servicios.add(otroServicio);

		cgp.setServicios(servicios);
	}

	@Test
	public void estaDisponibleRentasLunesALas1030() {
		momento = new DateTime("2016-04-25T10:30:00");
		Assert.assertTrue(cgp.estaDisponible(momento, "Rentas"));
		Assert.assertTrue(cgp.estaDisponible(momento, ""));
	}

}