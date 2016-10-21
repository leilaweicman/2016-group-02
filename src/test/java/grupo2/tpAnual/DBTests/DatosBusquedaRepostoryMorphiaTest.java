package grupo2.tpAnual.DBTests;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import com.mongodb.MongoClient;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.MorphiaService;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;

public class DatosBusquedaRepostoryMorphiaTest {

	/*
	 * public static void main(String[] args) {
	 * 
	 * DatosDeBusqueda datoBuscado; DatosDeBusqueda datoBuscado2;
	 * DatosDeBusqueda datoBuscado3; Comercio comercio; Rango unRango; Rango
	 * otroRango; Rango rango; List<Rango> listaRangos; List<POI> pois;
	 * DatosBusquedaRepositoryMongoDB repositorioDB;
	 * 
	 * 
	 * unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
	 * otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
	 * rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));
	 * listaRangos = Arrays.asList(unRango, otroRango, rango); comercio = new
	 * Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674),
	 * listaRangos); comercio.setId(4); pois = new ArrayList<>();
	 * pois.add(comercio); datoBuscado = new DatosDeBusqueda("lasHeras",
	 * "libros", 10, 15, new LocalDate(), pois); datoBuscado2 = new
	 * DatosDeBusqueda("flores", "carpetas", 14, 15, new
	 * LocalDate().minusDays(1), pois); datoBuscado3 = new
	 * DatosDeBusqueda("flores", "carpetas", 14, 15, new
	 * LocalDate().minusDays(2), pois);
	 * 
	 * try (MongoClient client = new MongoClient()) {
	 * 
	 * MorphiaService morphia = new MorphiaService(); morphia.getDatastore();
	 * 
	 * repositorioDB = new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class,
	 * morphia.getDatastore()); repositorioDB.agregarDatosBusqueda(datoBuscado);
	 * repositorioDB.agregarDatosBusqueda(datoBuscado2);
	 * repositorioDB.agregarDatosBusqueda(datoBuscado3); }
	 */

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
	
	@Before
	public void init() {

		unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));
		listaRangos = Arrays.asList(unRango, otroRango, rango);
		comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), listaRangos);
		comercio.setId(4);
		pois = new ArrayList<>();
		pois.add(comercio);
		datoBuscado = new DatosDeBusqueda("lasHeras", "libros", 10, 15, new LocalDate(), pois);
		datoBuscado2 = new DatosDeBusqueda("flores", "carpetas", 14, 15, new LocalDate().minusDays(1), pois);
		datoBuscado3 = new DatosDeBusqueda("flores", "carpetas", 14, 15, new LocalDate().minusDays(2), pois);

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

	/*@Test
	public void testConsultarDatos() {
		//List<DatosDeBusqueda> datosDeBusquedaList= new ArrayList<>();
		//datosDeBusquedaList=this.repositorioDB.consultarDatos();
		
		//Assert.assertEquals(datosDeBusquedaList.size(), 3);
	}
	
	@Test
	public void testObtenerTotalResultadosPorTerminal(){
		this.nombreTerminal="lasHeras";
		Assert.assertTrue(repositorioDB.obtenerTotalResultadosPorTerminal(nombreTerminal).get(1)==15);
	}
	
	@Test
	public void testCantidadDeBusquedasDe(){
		this.nombreTerminal="flores";
		Assert.assertTrue(repositorioDB.cantidadDeBusquedasDe(nombreTerminal)== 2);

		
	}
	
	@Test
	public void testObtenerPorNombre(){
		this.nombreTerminal="lasHeras";
		Assert.assertEquals(repositorioDB.obtenerPorNombre(nombreTerminal).size(), 1);	
	}*/
}