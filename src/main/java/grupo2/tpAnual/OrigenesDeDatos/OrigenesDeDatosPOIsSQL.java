package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import grupo2.tpAnual.Pois.POI;

public class OrigenesDeDatosPOIsSQL extends OrigenesDeDatosPOIs{
	
	private EntityManager em;
	
	public OrigenesDeDatosPOIsSQL(){
		em = PerThreadEntityManagers.getEntityManager();
	}
	
	public List<POI> getPOIs() {
		List<POI> pois = new ArrayList<POI>();
		pois = (List<POI>) em.createQuery("from POI").getResultList();
		return pois;
	}

	public void agregarPOI(POI poi) {
		em.persist(poi);
	}

	public void darDeBajaPOI(Integer id) {
		List<POI> pois = new ArrayList<POI>();
		pois = (List<POI>) em.createQuery("from POI where id = :id").setParameter("id", id).getResultList();
		pois.forEach(poi -> em.remove(poi));
	}

	@Override
	public List<POI> busqueda(String txtABuscar) {
		List<POI> resultados = new ArrayList<POI>();
		List<String> palClaves = Arrays.asList(txtABuscar);
		resultados =(List<POI>) em.createQuery("from POI where nombre= :nombre").setParameter("nombre", txtABuscar).getResultList(); 
		//resultados.addAll((List<POI>) em.createQuery("from POI where palabraClave in (:palabra)").setParameter("palabra", palClaves).getResultList());
		return resultados;
	}
	

}
