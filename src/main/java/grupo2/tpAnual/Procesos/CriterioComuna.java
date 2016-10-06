package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class CriterioComuna implements Criterio {
	@OneToOne
	public MemoryUserRepository repositorioUsuarios = new MemoryUserRepository();
	@OneToMany
	@JoinColumn
	private List<Usuario> listaUsuarios;

	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		listaUsuarios = new ArrayList<>();
		listaUsuarios.addAll(this.repositorioUsuarios.getUsuarios().stream().filter(usuario -> usuario.comuna == comuna)
				.collect(Collectors.toList()));
		return listaUsuarios;
	}

	public void setUsuarios(Usuario usuario) {
		this.repositorioUsuarios.setUsuario(usuario);

	}

	public void setRepositorioUsuarios(MemoryUserRepository repo) {
		this.repositorioUsuarios = repo;
	}

}
