package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.UserRepository;
import grupo2.tpAnual.Usuario;

public class CriterioComuna implements Criterio {
	@OneToOne
	public UserRepository repositorioUsuarios = new UserRepository();
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
		this.repositorioUsuarios.setUsuarios(usuario);

	}

	public void setRepositorioUsuarios(UserRepository repo) {
		this.repositorioUsuarios = repo;
	}

}
