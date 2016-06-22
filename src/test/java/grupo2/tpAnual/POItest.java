package grupo2.tpAnual;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

public class POItest {

	private POI santander;
	private Point coordenada;
	private Point coordenadaBondi;
	private Point coordenadaRopa;
	private Parada p114;
	private Comercio zara;
	private CGP rentas;
	private Comuna comuna1;

	private Point vertice1;
	private Point vertice2;
	private Point vertice3;
	private Point vertice4;
	private Rubro ropa;

	@Before
	public void init() {
		this.coordenada = Point.and(-34.666025, -58.385053);
		this.santander = new Banco();
	}

	@Test
	public void testEstaCercaBanco() {
		this.santander.setUbicacion(-34.666612, -58.3858490);
		Assert.assertTrue(santander.estaCerca(coordenada));
	}

	@Test
	public void testEstaCercaCGP() {
		this.rentas = new CGP();
		this.vertice1 = Point.and(-34.668075, -58.380060);
		this.vertice2 = Point.and(-34.673044, -58.387755);
		this.vertice3 = Point.and(-34.668363, -58.398441);
		this.vertice4 = Point.and(-34.661528, -58.388313);

		List<Point> listaVertices = Arrays.asList(vertice1, vertice2, vertice3, vertice4);
		this.comuna1 = new Comuna(1, listaVertices);
		this.rentas.setComuna(comuna1);
		this.rentas.setUbicacion(-34.667049, -58.384798);
		Assert.assertTrue(rentas.estaCerca(coordenada));
	}

	@Test
	public void testEstaCercaComercio() {
		Rango rango = new Rango();
		rango.setDia(3);
		rango.setHoraDesde(LocalTime.of(15, 0, 0));
		rango.setHoraHasta(LocalTime.of(18, 30, 0));
		List<Rango> listaRangos = Arrays.asList(rango);
		this.zara = new Comercio(listaRangos);
		this.ropa = new Rubro();
		this.zara.setRubro(ropa);
		this.ropa.setRadioCercania(0.3);
		this.zara.setUbicacion(-34.663575, -58.384333);
		this.coordenadaRopa = Point.and(-34.664775, -58.382917);

		Assert.assertTrue(zara.estaCerca(coordenadaRopa));
	}
	@Test
	public void testEstaPalabraClave() {
		this.santander.addPalabraClave("plazoFijo");
		Assert.assertTrue(santander.verificaPorTexto("plazoFijo"));
	}
	@Test
	public void testEstaCercaParada() {
		this.p114 = new Parada();
		this.p114.setUbicacion(-34.664837, -58.385674);
		this.coordenadaBondi = Point.and(-34.664634, -58.385459);
		Assert.assertTrue(p114.estaCerca(coordenadaBondi));
	}

}
