package grupo2.tpAnual.Procesos;

import java.util.List;

import javax.persistence.OneToOne;

import grupo2.tpAnual.Pois.Comuna;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class CriterioTodos implements Criterio {
	@OneToOne
	public MemoryUserRepository repositorioUsuarios;

	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		return this.repositorioUsuarios.getUsuarios();
	}

	public void setRepositorioUsuarios(MemoryUserRepository repo) {
		this.repositorioUsuarios = repo;
	}
}
