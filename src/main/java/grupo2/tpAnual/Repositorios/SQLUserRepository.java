package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class SQLUserRepository implements UserRepository, WithGlobalEntityManager{
	private static SQLUserRepository instance;

	public static SQLUserRepository get() {
		if (instance == null) {
			instance = new SQLUserRepository();
		}
		return instance;
}
	@Override
	public void deleteUsuario(Usuario usuario){
		entityManager().remove(usuario);
	}
	
	@Override
	public void saveUser(Usuario usuario) {
		entityManager().persist(usuario);
	}

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) entityManager().createQuery("from Usuario").getResultList();
		return usuarios;
	}

	@Override
	public List<Usuario> getUsuariosByComuna(Comuna comuna) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) entityManager().createQuery("from Usuario where comuna = :comuna").setParameter("comuna", comuna).getResultList();
		return usuarios;
	}
	
	@Override
	public Usuario getUsuarioByNombre(String nombre){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) entityManager().createQuery("from Usuario where nombre = :nombre").setParameter("nombre", nombre).getResultList();
		return usuarios.get(0);
	}

	@Override
	public List<Usuario> getUsauriosTerminal() {
		List<Usuario> terminales = new ArrayList<Usuario>();
		terminales = (List<Usuario>) entityManager().createQuery("from Usuario where esAdmin = 0").getResultList();
		return terminales;
	}

}
