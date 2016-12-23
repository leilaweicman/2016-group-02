package grupo2.tpAnual.DBTests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import grupo2.tpAnual.MorphiaService;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;

public class DatosBusquedaRepostoryMorphiaTest {

	private DatosDeBusqueda datoBuscado;
	private DatosDeBusqueda datoBuscado2;
	private DatosDeBusqueda datoBuscado3;
	private Comercio comercio;
	private Rango unRango;
	private Rango otroRango;
	private Rango rango;
	private String nombreTerminal;
	private List<Rango> listaRangos;
	private List<POI> pois;
	private DatosBusquedaRepositoryMongoDB repositorioDB;
	//private MongoClient client;
	MorphiaService morphia;
	private String nombreTerminal2;
	private List<Integer> listaTotResult;
	
	@Before
	public void init() {
		listaTotResult=new ArrayList<>();
		LocalDate today=LocalDate.now();
		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));
		listaRangos = Arrays.asList(unRango, otroRango, rango);
		comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), listaRangos);
		comercio.setId(4);
		pois = new ArrayList<>();
		pois.add(comercio);
		
		datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, today , pois);
		datoBuscado2 = new DatosDeBusqueda("flores", "carpetas", 14, 15, today.minusDays(1), pois);
		datoBuscado3 = new DatosDeBusqueda("flores", "carpetas", 14, 15, today.minusDays(2), pois);

		//try (MongoClient client = new MongoClient()) { //YA SE ESTA CREANDO EN MORPHIA SERVICE

			morphia = new MorphiaService();

			repositorioDB = new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class, morphia.getDatastore());
			repositorioDB.agregarDatosBusqueda(datoBuscado);
			repositorioDB.agregarDatosBusqueda(datoBuscado2);
			repositorioDB.agregarDatosBusqueda(datoBuscado3);
		//}
	}
	
	@After
	public void terminar(){
		morphia.getMongoClient().dropDatabase("mongo_persistance_dds");
	}

	@Test
	public void testConsultarDatos() {
		List<DatosDeBusqueda> datosDeBusquedaList= new ArrayList<>();
		datosDeBusquedaList=this.repositorioDB.consultarDatos();
		
		Assert.assertEquals(datosDeBusquedaList.size(), 3);
	}
	
	@Test
	public void testObtenerTotalResultadosPorTerminal(){
		this.listaTotResult.add(15);
		this.nombreTerminal="lasHeras";
		Assert.assertEquals(repositorioDB.obtenerTotalResultadosPorTerminal(nombreTerminal),this.listaTotResult);
	}
	
	@Test
	public void testObtenerTotalResultadosPorTerminal2(){
		this.listaTotResult.add(15);
		this.listaTotResult.add(15);
		this.nombreTerminal="flores";
		Assert.assertEquals(repositorioDB.obtenerTotalResultadosPorTerminal(nombreTerminal),this.listaTotResult);
	}
	
	@Test
	public void testCantidadDeBusquedasDe(){
		
		this.nombreTerminal="flores";
		Assert.assertTrue(repositorioDB.cantidadDeBusquedasDe(nombreTerminal)== 30);
		
	}
	
	@Test
	public void testObtenerPorNombre(){
		this.nombreTerminal="lasHeras";
		this.nombreTerminal2="flores";
		Assert.assertEquals(repositorioDB.obtenerPorNombre(nombreTerminal2).size(), 2);
		Assert.assertEquals(repositorioDB.obtenerPorNombre(nombreTerminal).size(), 1);	
	}
}