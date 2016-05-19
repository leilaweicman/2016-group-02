package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapaTest {
	private Mapa lasHeras;
	private POI santander;
	private CGP rentas;

	@Before
	public void init() {
		this.lasHeras = new Mapa();

		this.santander = new Banco();
		this.santander.addPalabraClave("plazoFijo");
		this.santander.addPalabraClave("dolar");

		this.rentas = new CGP();
		List<Servicio> servicios = new ArrayList<Servicio>();
		Servicio ser = new Servicio();
		ser.setNombre("Jubilados");
		servicios.add(ser);
		this.rentas.setServicios(servicios);

		this.lasHeras.agregarPOI(santander);
		this.lasHeras.agregarPOI(rentas);

	}

	@Test
	public void testEstaPalabraClave() {
		 Assert.assertTrue(santander.VerificarPorTexto("plazoFijo"));
	}

	/*@Test
	public void testBusquedaPorServicio() {
		//Assert.assertTrue(rentas.VerificarPorTexto("Jubilados"));
	}*/
	
	@Test
	public void testCrearPoiAcierto(){
		lasHeras.crearPOI("Banco");
		Assert.assertEquals(this.lasHeras.getPOIs().size(),3);
		
	}
	
	@Test
	public void testCrearPoiNoAcierto(){
		lasHeras.crearPOI("Peluca");
		Assert.assertEquals(this.lasHeras.getPOIs().size(),2);
		
	}

}