package grupo2.tpAnual.DBTests;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import grupo2.tpAnual.Repositorios.DatosBusquedaRepositoryMongoDB;
import grupo2.tpAnual.Repositorios.DatosDeBusqueda;
import grupo2.tpAnual.Repositorios.DatosDeBusquedaRepository;
import grupo2.tpAnual.MorphiaService;
import grupo2.tpAnual.Pois.POI;


public class DatosBusquedaRepostoryMorphiaTest {
	
	private static List<POI> pois;
	
	public static void main(String[] args) {
		
		pois= new ArrayList<>();
		DatosDeBusqueda datos = new DatosDeBusqueda("nombre", "texto", 60, 20, LocalDate.now(),pois);

	    try (MongoClient client = new MongoClient()) {
	     /* Morphia morphia = new Morphia();
	      morphia.mapPackage("model");

	      Datastore datastore = morphia.createDatastore(client, "datosDeBusqueda");*/

	      MorphiaService morphia= new MorphiaService();
	      morphia.getDatastore();
	      
	      DatosDeBusquedaRepository repositorio = new DatosBusquedaRepositoryMongoDB(DatosDeBusqueda.class, morphia.getDatastore()/*datastore*/);
	      repositorio.agregarDatosBusqueda(datos);
	    }
	  }
	
}
