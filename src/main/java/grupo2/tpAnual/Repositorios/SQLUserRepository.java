package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class SQLUserRepository implements UserRepository {
	private EntityManager em;
	private static SQLUserRepository instance;
	
	public SQLUserRepository() {
		em = PerThreadEntityManagers.getEntityManager();
		}

	public static SQLUserRepository get() {
		if (instance == null) {
			instance = new SQLUserRepository();
		}
		return instance;
}
	@Override
	public void deleteUsuario(Usuario usuario){
		em.remove(usuario);
	}
	
	@Override
	public void setUsuario(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) em.createQuery("from Usuario").getResultList();
		return usuarios;
	}

	@Override
	public List<Usuario> getUsuariosByComuna(Comuna comuna) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) em.createQuery("from Usuario where comuna = :comuna").setParameter("comuna", comuna).getResultList();
		return usuarios;
	}
	
	@Override
	public List<Usuario> getUsuariosByNombre(String nombre){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) em.createQuery("from Usuario where nombre = :nombre").setParameter("nombre", nombre).getResultList();
		return usuarios;
	}

}
