package grupo2.tpAnual.Web.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Repositorios.ComunaRepository;
import grupo2.tpAnual.Repositorios.MemoryComunaRepository;
import grupo2.tpAnual.Repositorios.SQLComunaRepository;
import grupo2.tpAnual.Web.Server;

public class SingletonComunaRepository implements WithGlobalEntityManager{ 
	public static ComunaRepository instance;
	private static EntityManager em;

	public static ComunaRepository get() {
		List<Point> points = new ArrayList<Point>();
		points.add(Point.and(-34.664837, -58.385674));
		
		Comuna comuna1 = new Comuna(1, points);
		Comuna comuna2 = new Comuna(4, points);
		
		
	
		if (instance == null) {
			if (Server.inMemory == true){				
				instance = new MemoryComunaRepository();
				instance.saveComuna(comuna1);
				instance.saveComuna(comuna2);
			}
			else{
				em = PerThreadEntityManagers.getEntityManager();
				em.getTransaction().begin();
				instance = SQLComunaRepository.get();
				instance.saveComuna(comuna1);
				instance.saveComuna(comuna2);				
				em.getTransaction().commit();
			}
		}
		return instance;
	}

}
