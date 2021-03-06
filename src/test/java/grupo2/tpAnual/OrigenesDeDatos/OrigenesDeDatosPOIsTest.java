package grupo2.tpAnual.OrigenesDeDatos;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.AccesoriosPois.Servicio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;

public class OrigenesDeDatosPOIsTest {
	private OrigenesDeDatosPOIsMemory origenesPOI;
	private Comercio kosiuko;
	private Banco bancoPiano;
	private CGP cgp;
	private Servicio jubilacion;
	private Rango unRango;
	private List<Rango> listaRangos;
	private List<Servicio> servicios;

	@Before
	public void init() {

		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		listaRangos = Arrays.asList(unRango);

		this.origenesPOI = new OrigenesDeDatosPOIsMemory();

		this.kosiuko = new Comercio("kosiuko", Point.and(-34.664837, -58.385674), listaRangos);
		this.kosiuko.setId(1444);
		this.bancoPiano = new Banco("Banco piano", Point.and(-34.664837, -58.385674));
		this.bancoPiano.setId(145);
		this.cgp = new CGP("Caballito", Point.and(-34.664837, -58.385674));
		this.cgp.setId(4);
	}

	@Test
	public void crearPoiTest() {
		this.origenesPOI.agregarPOI(kosiuko);
		Assert.assertTrue(this.origenesPOI.getPOIs().contains(kosiuko));
	}

	@Test
	public void darDeBajaPoiTest() {
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.darDeBajaPOI(kosiuko.getId());
		Assert.assertFalse(this.origenesPOI.getPOIs().contains(kosiuko));
	}

	@Test
	public void busquedaTestAssertTrue() {

		this.kosiuko.addPalabraClave("muchaGente");
		this.bancoPiano.addPalabraClave("muchaGente");
		this.cgp.addPalabraClave("hola");

		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(bancoPiano);

		Assert.assertTrue(this.origenesPOI.busqueda("muchaGente").contains(bancoPiano));
	}

	@Test
	public void busquedaTestAssertFalse() {

		this.kosiuko.addPalabraClave("muchaGente");
		this.bancoPiano.addPalabraClave("muchaGente");
		this.cgp.addPalabraClave("hola");

		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(bancoPiano);

		Assert.assertFalse(this.origenesPOI.busqueda("muchaGente").contains(cgp));
	}

	@Test
	public void busquedaTestFail() {
		this.jubilacion = new Servicio(listaRangos);
		this.jubilacion.setNombre("jubilacionesPami");
		servicios = Arrays.asList(jubilacion);
		this.cgp.setServicios(servicios);

		this.cgp.addPalabraClave("muchaGente");
		this.kosiuko.addPalabraClave("muchaGente");
		this.bancoPiano.addPalabraClave("muchaGente");

		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(bancoPiano);

		Assert.assertTrue(this.origenesPOI.busqueda("hola").isEmpty());
	}
}
