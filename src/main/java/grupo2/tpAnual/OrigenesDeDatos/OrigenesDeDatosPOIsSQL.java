package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo2.tpAnual.Pois.POI;

public class OrigenesDeDatosPOIsSQL extends OrigenesDeDatosPOIs implements WithGlobalEntityManager{

	private static OrigenesDeDatosPOIsSQL instance;
	
	public static OrigenesDeDatosPOIsSQL get() {
		if (instance == null) {
			instance = new OrigenesDeDatosPOIsSQL();
		}
		return instance;
}	
	public List<POI> getPOIs() {
		return entityManager().createQuery("from POI", POI.class).getResultList();
	}

	public void agregarPOI(POI poi) {
		entityManager().persist(poi);
	}

	public void darDeBajaPOI(Integer id) {
		List<POI> pois = new ArrayList<POI>();
		pois = entityManager().createQuery("from POI where id = :id", POI.class).setParameter("id", id).getResultList();
		pois.forEach(poi -> entityManager().remove(poi));
	}

	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<POI> resultados = new ArrayList<POI>();
		List<String> palClaves = Arrays.asList(txtABuscar);
		resultados = entityManager().createQuery("from POI where nombre= :nombre", POI.class).setParameter("nombre", txtABuscar).getResultList(); 
		return resultados;
	}
	
	@Override
	public POI buscarPorId(Integer id){
		List<POI> pois =  entityManager().createQuery("from POI where id= :id", POI.class).setParameter("id", id).getResultList(); 
		if(pois.size()> 0){
			return pois.get(0);
		}
		return null;	
	}
}
