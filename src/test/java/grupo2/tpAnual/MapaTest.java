package grupo2.tpAnual;

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
	private List<OrigenesDeDatos> listaDeOrigenes;
	private List<ObserverBusqueda> lista2;

	@Before
	public void init() {
		this.listaDeOrigenes = new ArrayList<OrigenesDeDatos>();
		
		this.datosBancosExternos = new OrigenesDeDatosBancoExterno();
		this.datosCentrosDTOs = new OrigenesDeDatosCentroDTO();
		this.origenesDeDatosPois = new OrigenesDeDatosPOIs();
		
		this.juan = new Usuario();

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

		this.origenesDeDatosPois.agregarPOI(santander);
		this.origenesDeDatosPois.agregarPOI(rentas);

		this.observerRegistro = new NotificarDatosBusqueda();
		this.observerMail = new EnviarMailBusqueda(2);

	}

	@Test
	public void testBusquedaPorElUsuarioSinObserversNiOrigenesDeDatos() {
		listaDeOrigenes = Arrays.asList();
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 0);
	}

	@Test
	public void testBusquedaPorElUsuarioConDatosDTO() {
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("palabras").size(), 2);
	}

	@Test
	public void testBusquedaPorElUsuarioConDatosPOIS() {
		listaDeOrigenes = Arrays.asList(origenesDeDatosPois);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 1);
	}

	@Test
	public void testBusquedaPorElUsuariConDatosBancoExterno() {
		listaDeOrigenes = Arrays.asList(datosBancosExternos);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("dolar").size(),2);
	}

	@Test
	public void testBusquedaPorElUsuarioConOrigenesDeDatos() {
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs, datosBancosExternos, origenesDeDatosPois);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertTrue(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").contains(santander));
	}

	@Test
	public void testBusquedaPorElUsuarioIntegrador_assertObservers() {
		lista2 = new ArrayList<>();
		lista2.add(observerMail);
		lista2.add(observerRegistro);
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs, datosBancosExternos, origenesDeDatosPois);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		juan.agregarObserversBusqueda(lista2);
		lasHeras.busquedaRealizadaPorElUsuario("plazoFijo");
		Assert.assertEquals(observerRegistro.getRegister().consultarDatos().size(),1);
	}

	@Test
	public void testBusquedaPorElUsuarioIntegrador_assertOrigenesDatos() {
		lista2 = new ArrayList<>();
		lista2.add(observerMail);
		lista2.add(observerRegistro);
		
		juan.agregarObserversBusqueda(lista2);
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs, datosBancosExternos, origenesDeDatosPois);
		this.lasHeras = new Mapa(listaDeOrigenes);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 5);
	}

}