package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class CriterioSeleccionFija implements Criterio {
	@OneToOne
	public MemoryUserRepository repositorioUsuarios;
	
	@OneToMany
	@JoinColumn
	public List<Usuario> listaUsers = new ArrayList<>();

	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		return this.listaUsers;
	}

	public void setRepositorioUsuarios(MemoryUserRepository repo) {
		this.repositorioUsuarios = repo;
	}

	public void agregarUsuario(Usuario user) {
		this.listaUsers.add(user);
	}
}
