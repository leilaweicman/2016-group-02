package grupo2.tpAnual.Web.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo2.tpAnual.MorphiaService;
import grupo2.tpAnual.AccesoriosPois.Rango;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;
import grupo2.tpAnual.Web.Server;

public class SingletonDatosBusquedaRepository extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	private static DatosDeBusquedaRepository instance;
	private static EntityManager em;	
	private static MorphiaService morphia;
	
	private static DatosDeBusqueda datoBuscado;
	private static DatosDeBusqueda datoBuscado2;
	private static DatosDeBusqueda datoBuscado3;
	private static Comercio comercio;
	private static Rango unRango;
	private static Rango otroRango;
	private static Rango rango;
	//private static String nombreTerminal;
	private static List<Rango> listaRangos;
	private static List<POI> pois;
	//private static String nombreTerminal2;
	private static List<Integer> listaTotResult;
	
	public static DatosDeBusquedaRepository get() {
		if (instance == null) {
			if (Server.inMemory == true)
				inMemory();
			else
				inDB();
		}
		return instance;
	}
	
	private static void inDB() {
		
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
	
		morphia = new MorphiaService(); //TODO no se si se pone el new
		
		instance = new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class, morphia.getDatastore());

		instance = new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class, morphia.getDatastore());
		instance.agregarDatosBusqueda(datoBuscado);
		instance.agregarDatosBusqueda(datoBuscado2);
		instance.agregarDatosBusqueda(datoBuscado3);
		
	}

	private static void inMemory() {
		/*Usuario usuario = new Usuario();
		List<OrigenesDeDatos> listaDeOrigenes = new ArrayList<>();
		DatosDeBusquedaRepository repositorioDatosBusqueda = new DatosBusquedaRepositoryMemory();
		OrigenesDeDatosPOIs pois = OrigenesDeDatosPOIsMemory.get();
		agregarPoisAlRepo(pois);
		listaDeOrigenes = Arrays.asList(pois);
		instance = new Mapa(listaDeOrigenes, repositorioDatosBusqueda);
		instance.setUsuario(usuario);*/
	}

	
	
}
