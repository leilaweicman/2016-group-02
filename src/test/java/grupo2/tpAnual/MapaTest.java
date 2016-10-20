package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;

import ServiciosExternos.CentroDTO;
import ServiciosExternos.ServicioExternoBanco;
import ServiciosExternos.ServicioExternoCentroDTO;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.AccesoriosPois.Servicio;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosBancoExterno;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosCentroDTO;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.Usuario;

public class MapaTest {
	private Usuario juan;
	private Mapa lasHeras;
	private POI santander;
	private CGP rentas;
	private OrigenesDeDatosPOIsMemory origenesDeDatosPois;
	private OrigenesDeDatosCentroDTO datosCentrosDTOs;
	private OrigenesDeDatosBancoExterno datosBancosExternos;
	private List<OrigenesDeDatos> listaDeOrigenes;
	private ServicioExternoBanco bancosStub;
	private ServicioExternoCentroDTO centrosStub;
	private DatosBusquedaRepositoryMemory repositorioDB;
	
	@Before
	public void init() {
		this.repositorioDB = new DatosBusquedaRepositoryMemory();
		this.listaDeOrigenes = new ArrayList<OrigenesDeDatos>();

		this.bancosStub = Mockito.mock(ServicioExternoBanco.class);

		this.centrosStub = Mockito.mock(ServicioExternoCentroDTO.class);
		this.datosCentrosDTOs = new OrigenesDeDatosCentroDTO(centrosStub);
		this.origenesDeDatosPois = new OrigenesDeDatosPOIsMemory();

		this.juan = new Usuario();

		this.santander = new Banco("Santander", Point.and(-34.664837, -58.385674));
		this.santander.addPalabraClave("plazoFijo");
		this.santander.addPalabraClave("dolar");

		this.rentas = new CGP("Flores", Point.and(-34.664837, -58.385674));
		List<Servicio> servicios = new ArrayList<Servicio>();
		List<Rango> listaRango = new ArrayList<>();
		Servicio ser = new Servicio(listaRango);
		ser.setNombre("Jubilados");
		servicios.add(ser);
		this.rentas.setServicios(servicios);

		this.origenesDeDatosPois.agregarPOI(santander);
		this.origenesDeDatosPois.agregarPOI(rentas);

	}

	@Test
	public void testBusquedaPorElUsuarioSinObserversNiOrigenesDeDatos() {
		listaDeOrigenes = Arrays.asList();
		this.lasHeras = new Mapa(listaDeOrigenes,repositorioDB);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 0);
	}

	@Test
	public void testBusquedaPorElUsuarioConDatosDTO() {
		List<CentroDTO> respuesta = Arrays.asList(new CentroDTO(1, "Juan B Justo 1841"));
		Mockito.when(centrosStub.busqueda("centros")).thenReturn(respuesta);
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs);
		this.lasHeras = new Mapa(listaDeOrigenes, repositorioDB);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("centros").get(0).getDireccion().getCalle(),
				"Juan B Justo 1841");

	}

	@Test
	public void testBusquedaPorElUsuarioConDatosPOIS() {
		listaDeOrigenes = Arrays.asList(origenesDeDatosPois);
		this.lasHeras = new Mapa(listaDeOrigenes, repositorioDB);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 1);
	}

	@Test
	public void testBusquedaPorElUsuariConDatosBancoExterno() {
		Mockito.when(bancosStub.busqueda("extracciones", ""))
				.thenReturn("[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
						+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
						+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
						+ " }" + "]");
		this.datosBancosExternos = new OrigenesDeDatosBancoExterno(bancosStub);
		listaDeOrigenes = Arrays.asList(datosBancosExternos);
		this.lasHeras = new Mapa(listaDeOrigenes, repositorioDB);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("extracciones").get(0).getClass(), Banco.class);
	}

	@Test
	public void testBusquedaPorElUsuarioConOrigenesDeDatos() {
		Mockito.when(bancosStub.busqueda("plazoFijo", ""))
				.thenReturn("[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
						+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
						+ " \"servicios\": [ \"plazoFijo\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
						+ " }" + "]");
		this.datosBancosExternos = new OrigenesDeDatosBancoExterno(bancosStub);
		listaDeOrigenes = Arrays.asList(datosCentrosDTOs, datosBancosExternos, origenesDeDatosPois);
		List<CentroDTO> respuesta = Arrays.asList(new CentroDTO(1, "Pedro Goyena 1825"));
		Mockito.when(centrosStub.busqueda("plazoFijo")).thenReturn(respuesta);
		this.lasHeras = new Mapa(listaDeOrigenes, repositorioDB);
		this.lasHeras.setUsuario(juan);
		Assert.assertEquals(lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 3);
	}

}