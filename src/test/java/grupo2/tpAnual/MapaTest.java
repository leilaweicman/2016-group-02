package grupo2.tpAnual;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo2.tpAnual.Observers.EnviarMailBusqueda;
import grupo2.tpAnual.Observers.NotificarDatosBusqueda;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;

public class MapaTest {
	private Usuario juan;
	private Mapa lasHeras;
	private POI santander;
	private CGP rentas;
	private OrigenesDeDatosPOIs origenesDeDatosPois;
	private EnviarMailBusqueda observerMail;
	private NotificarDatosBusqueda observerRegistro;
	private OrigenesDeDatosCentroDTO datosCentrosDTOs;
	private OrigenesDeDatosBancoExterno datosBancosExternos;
	private ByteArrayOutputStream outContent;
	private List<OrigenesDeDatos> listaDeOrigenes;
	private List<ObserverBusqueda> listaMail;
	private List<ObserverBusqueda> lista2;

	@Before
	public void init() {
		this.listaDeOrigenes = new ArrayList<OrigenesDeDatos>();
		this.datosBancosExternos = new OrigenesDeDatosBancoExterno();
		this.datosCentrosDTOs = new OrigenesDeDatosCentroDTO();
		this.origenesDeDatosPois = new OrigenesDeDatosPOIs();
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs, datosBancosExternos, origenesDeDatosPois);

		this.juan = new Usuario();

		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);

		this.santander = new Banco();
		this.santander.addPalabraClave("plazoFijo");
		this.santander.addPalabraClave("dolar");

		this.rentas = new CGP();
		List<Servicio> servicios = new ArrayList<Servicio>();
		List<Rango> listaRango = new ArrayList<>();
		Servicio ser = new Servicio(listaRango);
		ser.setNombre("Jubilados");
		servicios.add(ser);
		this.rentas.setServicios(servicios);

		// this.lasHeras.agregarPOI(santander); ahora se encarga el
		// origenDeDatosPOIs de agregarlos
		this.origenesDeDatosPois.agregarPOI(santander);
		this.origenesDeDatosPois.agregarPOI(rentas);

		this.observerRegistro = new NotificarDatosBusqueda();
		this.observerMail = new EnviarMailBusqueda(2);

		listaMail = new ArrayList<>();
		lista2 = new ArrayList<>();
		listaMail.add(observerMail);
		lista2.add(observerMail);
		lista2.add(observerRegistro);

		this.outContent = new ByteArrayOutputStream();
	}

	@Test
	public void testBusquedaPorElUsuarioSinObserversNiOrigenesDeDatos() {
		this.lasHeras.sacarOrigenesDeDatos(datosBancosExternos);
		this.lasHeras.sacarOrigenesDeDatos(datosCentrosDTOs);
		this.lasHeras.sacarOrigenesDeDatos(origenesDeDatosPois);

		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 0);
	}

	@Test
	public void testBusquedaPorElUsuarioConDatosDTO() {

		this.lasHeras.sacarOrigenesDeDatos(origenesDeDatosPois);
		this.lasHeras.sacarOrigenesDeDatos(datosBancosExternos);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("palabras").size(), 2);
	}

	@Test
	public void testBusquedaPorElUsuarioConDatosPOIS() {

		this.lasHeras.sacarOrigenesDeDatos(datosBancosExternos);
		this.lasHeras.sacarOrigenesDeDatos(datosCentrosDTOs);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 1);
	}

	@Test
	public void testBusquedaPorElUsuariConDatosBancoExterno() {
		this.lasHeras.sacarOrigenesDeDatos(datosCentrosDTOs);
		this.lasHeras.sacarOrigenesDeDatos(origenesDeDatosPois);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("dolar").size(), 2);
	}

	@Test
	public void testBusquedaPorElUsuarioConOrigenesDeDatos() {

		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 5);
	}

	@Test
	public void testBusquedaPorElUsuarioConObservers() {
		juan.agregarObserversBusqueda(listaMail);
		System.setOut(new PrintStream(outContent));

		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Assert.assertEquals("La busqueda se ejecuto correctamente", outContent.toString());
	}

	@Test
	public void testBusquedaPorElUsuarioIntegrador_assertObservers() {
		juan.agregarObserversBusqueda(lista2);
		System.setOut(new PrintStream(outContent));
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Assert.assertEquals("La busqueda se ejecuto correctamente", outContent.toString());
	}

	@Test
	public void testBusquedaPorElUsuarioIntegrador_assertOrigenesDatos() {
		juan.agregarObserversBusqueda(lista2);
		System.setOut(new PrintStream(outContent));
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");

		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 5);
	}

}