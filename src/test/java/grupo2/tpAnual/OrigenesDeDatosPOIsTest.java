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
	private Banco bancoPiano;
	private CGP cgp;
	private Servicio jubilacion;
	private Rango unRango;
	private List<Rango> listaRangos;
	private List<Servicio> servicios;

	
	@Before
	public void init() {
		
		unRango = new Rango();
		unRango.setDia(1);
		unRango.setHoraDesde(LocalTime.of(9, 0, 0));
		unRango.setHoraHasta(LocalTime.of(18, 0, 0));
		listaRangos = Arrays.asList(unRango);

		this.origenesPOI = new OrigenesDeDatosPOIs();
		
		this.kosiuko = new Comercio(listaRangos);
		this.kosiuko.setNumeroVerificador(1444);
		this.bancoPiano = new Banco();
		this.bancoPiano.setNumeroVerificador(145);
		this.cgp = new CGP();
		this.cgp.setNumeroVerificador(4);
	}

	@Test
	public void crearPoiTest() {
		this.origenesPOI.agregarPOI(kosiuko);
		Assert.assertEquals(this.origenesPOI.getPOIs().size(), 1);
	}

	@Test
	public void darDeBajaPoiTest() {
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(bancoPiano);
		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.darDeBajaPOI(kosiuko.getNumeroVerificador());
		Assert.assertEquals(this.origenesPOI.getPOIs().size(), 2);
	}

	@Test
	public void busquedaTestAssert() {

		this.kosiuko.addPalabraClave("muchaGente");
		this.bancoPiano.addPalabraClave("muchaGente");
		this.cgp.addPalabraClave("muchaGente");

		this.origenesPOI.agregarPOI(cgp);
		this.origenesPOI.agregarPOI(kosiuko);
		this.origenesPOI.agregarPOI(bancoPiano);

		Assert.assertEquals(this.origenesPOI.busqueda("muchaGente").size(), 3);
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

		Assert.assertEquals(this.origenesPOI.busqueda("hola").size(), 0);
	}
}
