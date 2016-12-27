package grupo2.tpAnual.Procesos;

import java.util.List;

import javax.persistence.Transient;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;

public class CriterioTodos implements Criterio {
	@Transient
	public MemoryUserRepository repositorioUsuarios;

	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		return this.repositorioUsuarios.getUsuarios();
	}

	public void setRepositorioUsuarios(MemoryUserRepository repo) {
		this.repositorioUsuarios = repo;
	}
}
