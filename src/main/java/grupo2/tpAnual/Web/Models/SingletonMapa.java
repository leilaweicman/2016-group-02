package grupo2.tpAnual.Web.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.Mapa;
import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.AccesoriosPois.Direccion;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatos;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIs;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsMemory;
import grupo2.tpAnual.OrigenesDeDatos.OrigenesDeDatosPOIsSQL;
import grupo2.tpAnual.Pois.Banco;
import grupo2.tpAnual.Pois.CGP;
import grupo2.tpAnual.Pois.Comercio;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Pois.Parada;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMemory;
import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class SingletonMapa {
	private static Mapa instanceDB;
	private static Mapa instanceMemory;

	public static Mapa getDB() {
		if (instanceDB == null) {
			Usuario usuario = new Usuario();
			List<OrigenesDeDatos> listaDeOrigenes = new ArrayList<>();
			DatosDeBusquedaRepository repositorioDatosBusqueda = new DatosBusquedaRepositoryMongoDB(null,null);
			OrigenesDeDatosPOIs pois = OrigenesDeDatosPOIsSQL.get();
			agregarPoisAlRepo(pois);
			listaDeOrigenes= Arrays.asList(pois);
			instanceDB = new Mapa(listaDeOrigenes, repositorioDatosBusqueda);
			instanceDB.setUsuario(usuario);
	
		}
		return instanceDB;
	}
	
	public static Mapa getMemory() {
		if (instanceMemory == null) {
			Usuario usuario = new Usuario();
			List<OrigenesDeDatos> listaDeOrigenes = new ArrayList<>();
			DatosDeBusquedaRepository repositorioDatosBusqueda = new DatosBusquedaRepositoryMemory();
			OrigenesDeDatosPOIs pois = OrigenesDeDatosPOIsMemory.get();
			agregarPoisAlRepo(pois);
			listaDeOrigenes= Arrays.asList(pois);
			instanceMemory = new Mapa(listaDeOrigenes,repositorioDatosBusqueda);
			instanceMemory.setUsuario(usuario);
		}
		return instanceMemory;
	}
	
	public static void agregarPoisAlRepo(OrigenesDeDatosPOIs repo){
		POI banco = new Banco("Banco Nación", Point.and(12.584, -39.458));
		POI comercio = new Comercio();
		POI cgp = new CGP("Comuna 6", Point.and(15.674, -12.432));
		POI parada = new Parada("Linea 48", Point.and(-98.342, -39.342),"Los amigos sa");
		
		List<Point> points = Arrays.asList(Point.and(15.674, -12.432), Point.and(-98.342, -39.342),Point.and(12.584, -39.458));
		Comuna comuna6 = new Comuna(6,points);	
		
		List<String> pcBanco = Arrays.asList("Tarjeta Nativa", "Moneda extranjera", "dolar", "depositos");
		List<String> pcComercio = Arrays.asList("Ahora 12", "indumentaria femenina", "ropa");
		List<String> pcCGP = Arrays.asList("Cursos","Actividades","Comunas","Tarjeta Nativa");
		List<String> pcParada = Arrays.asList("48","Los amigos sa","paradas por rivadavia", "rivadavia derecho");
		
		banco.setId(1);
		banco.setComuna(comuna6);
		banco.setDireccion(new Direccion("Pedro goyena 525","Caballito"));
		banco.setPalabrasClaves(pcBanco);
		repo.agregarPOI(banco);
		
		cgp.setId(2);
		cgp.setComuna(comuna6);
		cgp.setDireccion(new Direccion("Parque Centenario","Caballito"));
		cgp.setPalabrasClaves(pcCGP);
		repo.agregarPOI(cgp);
		
		parada.setId(3);
		parada.setComuna(comuna6);
		parada.setDireccion(new Direccion("Rivadavia 5520","Caballito"));
		parada.setPalabrasClaves(pcParada);
		repo.agregarPOI(parada);
		
		comercio.setId(4);
		comercio.setComuna(comuna6);
		comercio.setDireccion(new Direccion("Acoyte y Rivadavia","Caballito"));
		comercio.setNombre("Zara");
		comercio.setPalabrasClaves(pcComercio);
		repo.agregarPOI(comercio);
	}
}
