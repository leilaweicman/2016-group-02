package grupo2.tpAnual;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbar.geodds.Point;

import com.mongodb.MongoClient;

public class MongoTest {
	private MongoClient mongo;
	private Morphia morphia;
	private Datastore store;
	
	@Before 
	public void init(){
		morphia = new Morphia();
		mongo = new MongoClient();
		morphia.mapPackage("grupo2.tpAnual");
		store = morphia.createDatastore(mongo, "BasePOIS");
	}
	
	@After
	public void terminar(){
		mongo.dropDatabase("BasePOIS");
	}
	
	//@Test
	public void probandoPersistenciaComercio(){
		Rango unRango = new Rango(1, LocalTime.of(9, 0, 0), LocalTime.of(18, 0, 0));
		Rango otroRango = new Rango(3, LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		Rango rango = new Rango(3, LocalTime.of(15, 0, 0), LocalTime.of(18, 30, 0));

		Comercio comercio = new Comercio("Supermercado argenChino", Point.and(-34.664837, -58.385674), Arrays.asList(unRango, otroRango, rango));
		store.save(comercio);
		
		Comercio comercioGet = store.createQuery(Comercio.class)
				.field("nombre").equal("Supermercado argenChino").get();
		Assert.assertEquals(comercioGet.getRango(), Arrays.asList(unRango, otroRango, rango));
	}
	//@Test
	public void probandoPersistenciaRubro(){
		Rubro rubro = new Rubro();
		rubro.setTipo("comida");
		rubro.setRadioCercania(2);
		store.save(rubro);
		
		Rubro ru = store.createQuery(Rubro.class)
				.field("tipoRubro").equal("comida").get();
		Assert.assertEquals(ru.getRadioCercania(),2);
	}
}
