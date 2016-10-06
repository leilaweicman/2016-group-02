package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class SQLUserRepository implements UserRepository {
	private EntityManager em;

	public SQLUserRepository() {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("db");
		em = emf.createEntityManager();
		}
	
	@Override
	public void deleteUsuario(Usuario usuario){
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
	}
	
	@Override
	public void setUsuario(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}

	@Override
	public List<Usuario> getUsuarios() {
		em.getTransaction().begin();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) em.createQuery("from Usuario").getResultList();
		em.getTransaction().commit();
		return usuarios;
	}

	@Override
	public List<Usuario> getUsuariosByComuna(Comuna comuna) {
		em.getTransaction().begin();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) em.createQuery("from Usuario where comuna = :comuna").setParameter("comuna", comuna).getResultList();
		em.getTransaction().commit();
		return usuarios;
	}

}
