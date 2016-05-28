package grupo2.tpAnual;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import grupo2.tpAnual.Integraciones.IntegracionBancoExterno;
import grupo2.tpAnual.Integraciones.IntegracionCentroDTO;

public class MapaTest {
	private Mapa lasHeras;
	private POI santander;
	private CGP rentas;
	private EnviarMailBusqueda observerMail;
	private RegistrarBusqueda observerRegistro;
	private IntegracionCentroDTO centroDTOmock;
	private IntegracionBancoExterno bancoExternoMock;
	private ByteArrayOutputStream outContent;

	@Before
	public void init() {
		this.lasHeras = new Mapa();
		this.lasHeras.setTiempoMaximoDeEjecucion(3);

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

		this.lasHeras.agregarPOI(santander);
		this.lasHeras.agregarPOI(rentas);

		this.observerRegistro = new RegistrarBusqueda();
		this.observerMail = new EnviarMailBusqueda();

		centroDTOmock = Mockito.mock(IntegracionCentroDTO.class);
		bancoExternoMock = Mockito.mock(IntegracionBancoExterno.class);
		List<POI> pois = Arrays.asList(rentas, santander);

		Mockito.when(centroDTOmock.busqueda("plazoFijo")).thenReturn(pois);
		Mockito.when(bancoExternoMock.busqueda("plazoFijo")).thenReturn(pois);
		Mockito.when(bancoExternoMock.busqueda("dolar")).thenReturn(pois);
		
		outContent = new ByteArrayOutputStream();
	}

	@Test
	public void testEstaPalabraClave() {
		Assert.assertTrue(santander.verificarPorTexto("plazoFijo"));
	}

	/*
	 * @Test public void testBusquedaPorServicio() {
	 * //Assert.assertTrue(rentas.VerificarPorTexto("Jubilados")); }
	 */

	@Test
	public void testCrearPoiAcierto() {
		lasHeras.crearPOI(rentas);
		Assert.assertEquals(this.lasHeras.getPOIs().size(), 3);
	}

	@Test
	public void testBusquedaPorElUsuarioSinObserversNiIntegraciones() {
		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 1);
	}

	@Test
	public void testBusquedaPorElUsuarioConIntegracionDTO() {
		long tiempoInicio = System.currentTimeMillis();

		lasHeras.setOrigenesDeDatos(centroDTOmock);
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Mockito.verify(centroDTOmock, Mockito.after(30000)).busqueda("plazoFijo");
		
		long tiempoFin = System.currentTimeMillis();
		Assert.assertTrue((tiempoFin - tiempoInicio) / 1000 > lasHeras.getTiempoMaximoDeEjecucion());
	}

	@Test
	public void testBusquedaPorElUsuariConIntegracionBancoExterno() {

		lasHeras.setOrigenesDeDatos(bancoExternoMock);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("dolar").size(), 3);
	}

	@Test
	public void testBusquedaPorElUsuarioConIntegracioneS() {
		lasHeras.setOrigenesDeDatos(centroDTOmock);
		lasHeras.setOrigenesDeDatos(bancoExternoMock);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 5);
	}

	@Test
	public void testBusquedaPorElUsuarioConObserverRegistro() {
		lasHeras.agregarObserverBusqueda(observerRegistro);
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Assert.assertEquals(observerRegistro.getRegistroBusqueda().size(), 1);

	}

	@Test
	public void testBusquedaPorElUsuarioConObservers() {
		lasHeras.agregarObserverBusqueda(observerMail);
		lasHeras.agregarObserverBusqueda(observerRegistro);
		System.setOut(new PrintStream(outContent));
		
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Assert.assertEquals("La busqueda se ejecuto correctamente", outContent.toString());

		lasHeras.busquedaRealizadaPorElUsuario("hola");
		Assert.assertEquals(observerRegistro.getRegistroBusqueda().size(), 2);
	}

	@Test
	public void testBusquedaPorElUsuarioConObserversEIntegraciones() {
		lasHeras.agregarObserverBusqueda(observerMail);
		lasHeras.agregarObserverBusqueda(observerRegistro);
		lasHeras.setOrigenesDeDatos(bancoExternoMock);
		lasHeras.setOrigenesDeDatos(centroDTOmock);
		System.setOut(new PrintStream(outContent));
		
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		
		//Assert.assertEquals("Se envio el mail correctamente", outContent.toString());
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 5);
		lasHeras.busquedaRealizadaPorElUsuario("hola");
		Assert.assertEquals(observerRegistro.getRegistroBusqueda().size(), 3); //son tres por el assert anterior

	}

}