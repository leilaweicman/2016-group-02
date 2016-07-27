package grupo2.tpAnual.Procesos;

import java.util.List;

import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.UserRepository;
import grupo2.tpAnual.Usuario;

public class CriterioTodos implements Criterio{
	public UserRepository repositorioUsuarios;
	
	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {		
		return this.repositorioUsuarios.getUsuarios();		
	}
	
	public void setRepositorioUsuarios(UserRepository repo){
		this.repositorioUsuarios=repo;
	}
}
