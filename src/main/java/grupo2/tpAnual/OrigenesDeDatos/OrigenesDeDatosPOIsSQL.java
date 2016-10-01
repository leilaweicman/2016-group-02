package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import grupo2.tpAnual.Pois.POI;

public class OrigenesDeDatosPOIsSQL implements OrigenesDeDatosPOIs{
	
	private EntityManager em;
	
	public OrigenesDeDatosPOIsSQL(){
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("db");
		em = emf.createEntityManager();
	}
	
	public List<POI> getPOIs() {
		em.getTransaction().begin();
		List<POI> pois = new ArrayList<POI>();
		pois = (List<POI>) em.createQuery("from POI").getResultList();
		em.getTransaction().commit();
		return pois;
	}

	public void agregarPOI(POI poi) {
		em.getTransaction().begin();
		em.persist(poi);
		em.getTransaction().commit();
	}

	public void darDeBajaPOI(Integer id) {
		em.getTransaction().begin();
		List<POI> pois = new ArrayList<POI>();
		pois = (List<POI>) em.createQuery("from POI where id = :id").setParameter("id", id).getResultList();
		pois.forEach(poi -> em.remove(poi));
		em.getTransaction().commit();
	}

	@Override
	public List<POI> busqueda(String txtABuscar) {
		em.getTransaction().begin();
		List<POI> resultados = new ArrayList<POI>();
		resultados =(List<POI>) em.createQuery("from POI where nombre= :nombre").setParameter("nombre", txtABuscar).getResultList(); 
		//resultados.addAll((List<POI>) em.createQuery("from POI where palabraClave in :palabra").setParameter("palabra", txtABuscar).getResultList());
		em.getTransaction().commit();
		return resultados;
	}
	

}
