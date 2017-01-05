package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class SQLComunaRepository implements ComunaRepository, WithGlobalEntityManager {

	private static SQLComunaRepository instance;
	
	public static SQLComunaRepository get() {
		if (instance == null) {
			instance = new SQLComunaRepository();
		}
		return instance;
	}
	
	@Override
	public void deleteComuna(Comuna comuna) {
		entityManager().remove(comuna);
	}

	@Override
	public void saveComuna(Comuna comuna) {
		entityManager().persist(comuna);
	}

	@Override
	public List<Comuna> getComunas() {
		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = (List<Comuna>) entityManager().createQuery("from Comuna").getResultList();
		return comunas;
	}

	@Override
	public Comuna getComunaByNumero(int numero) {
		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = (List<Comuna>) entityManager().createQuery("from Comuna where numeroComuna = :numero").setParameter("numero", numero).getResultList();
		return comunas.get(0);
	}

}
