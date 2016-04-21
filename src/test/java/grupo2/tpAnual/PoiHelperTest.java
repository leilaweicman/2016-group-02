package grupo2.tpAnual;



import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import junit.framework.Assert;

public class PoiHelperTest {
	private PoiHelper lasHeras;
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
	public void init(){
		this.coordenada = Point.and(-34.666025, -58.385053);
		this.lasHeras = new PoiHelper();
	
		this.santander = new Banco();
		this.santander.setUbicacion(-34.666612, -58.3858490);
		this.santander.addPalabraClave("plazoFijo");
		this.santander.addPalabraClave("dolar");
		
		this.p114 = new Parada();
		this.p114.setUbicacion(-34.664837, -58.385674);
		this.coordenadaBondi = Point.and(-34.664634, -58.385459);
		
		this.zara = new Comercio();
		this.zara.setUbicacion(-34.663575, -58.384333);
		this.coordenadaRopa = Point.and(-34.664775, -58.382917);
		
		this.rentas = new CGP();
		this.rentas.setUbicacion(-34.667049, -58.384798);
		List<Servicio> servicios = new ArrayList<Servicio>();
		Servicio ser = new Servicio();
		ser.setNombre("Jubilados");
		servicios.add(ser);
		this.rentas.setServicios(servicios);
		
		this.vertice1 = Point.and(-34.668075, -58.380060);
		this.vertice2= Point.and(-34.673044, -58.387755);
		this.vertice3=Point.and(-34.668363, -58.398441);
		this.vertice4=Point.and(-34.661528, -58.388313);
		
		this.comuna1 = new Comuna();
		this.comuna1.setComuna();
		this.comuna1.agregarVertice(vertice1);
		this.comuna1.agregarVertice(vertice2);
		this.comuna1.agregarVertice(vertice3);
		this.comuna1.agregarVertice(vertice4);
		
		this.lasHeras.agregarPOI(santander);
		this.lasHeras.agregarPOI(rentas);
		this.rentas.setComuna(comuna1);
		
		this.ropa= new Rubro();
		this.zara.setRubro(ropa);
		this.ropa.setRadioCercania(0.3);
		
	}
	
	@Test
	public void testEstaPalabraClave(){
		Assert.assertTrue(santander.Busqueda("plazoFijo"));
	}
	
	@Test
	public void testBusquedaPorServicio(){
		Assert.assertTrue(rentas.Busqueda("Jubilados"));
	}
	
	
	@Test
	public void testEstaCercaBanco() {
		Assert.assertTrue(lasHeras.estanCerca(santander,coordenada));
	}

	@Test
	public void testEstaCercaCGP(){
		Assert.assertTrue(lasHeras.estanCerca(rentas, coordenada));
	}
	
	@Test
	public void testEstaCercaComercio(){
		Assert.assertTrue(lasHeras.estanCerca(zara, coordenadaRopa));
	}
	
	@Test
	public void testEstaCercaParada(){
		Assert.assertTrue(lasHeras.estanCerca(p114, coordenadaBondi));
	}
}