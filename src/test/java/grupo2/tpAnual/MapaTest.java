package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MapaTest {
	private Mapa lasHeras;
	private POI santander;
	private CGP rentas;
	private EnviarMailBusqueda observerMail = Mockito.mock(EnviarMailBusqueda.class);
	private RegistrarBusqueda observerRegristro = Mockito.mock(RegistrarBusqueda.class);

	@Before
	public void init() {
		this.lasHeras = new Mapa();

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
	public void testBusquedaPorElUsuarioSinObservers() { //cuando se arregle el del banco, va a haber que modificar este test
		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 3);
	}
	
	@Test
	public void testBusquedaPorElUsuarioConObservers() { //cuando se arregle el del banco, va a haber que modificar este test
		lasHeras.agregarObserverBusqueda(observerMail);
		lasHeras.agregarObserverBusqueda(observerRegristro);
		Assert.assertEquals(this.lasHeras.busquedaRealizadaPorElUsuario("plazoFijo").size(), 3);
	//Con esto verifico que al agregar observers, todo sigue funcionando bien. El funcionamiento de cada observer está testeado en sus clases correspondientes
	}

}