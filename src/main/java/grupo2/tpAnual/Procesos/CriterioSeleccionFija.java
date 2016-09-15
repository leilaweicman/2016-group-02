package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.UserRepository;
import grupo2.tpAnual.Usuario;

public class CriterioSeleccionFija implements Criterio {
	@OneToOne
	public UserRepository repositorioUsuarios;
	
	@OneToMany
	@JoinColumn
	public List<Usuario> listaUsers = new ArrayList<>();

	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		return this.listaUsers;
	}

	public void setRepositorioUsuarios(UserRepository repo) {
		this.repositorioUsuarios = repo;
	}

	public void agregarUsuario(Usuario user) {
		this.listaUsers.add(user);
	}
}
