package grupo2.tpAnual.Procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.UserRepository;
import grupo2.tpAnual.Usuario;

public class CriterioComuna implements Criterio{

	public UserRepository repositorioUsuarios=new UserRepository();
	private List<Usuario> lista;
	
	@Override
	public List<Usuario> dameUsuarios(Comuna comuna) {
		lista=new ArrayList<>();
		lista.addAll(this.repositorioUsuarios.getUsuarios().stream().
				filter(usuario -> usuario.comuna == comuna).collect(Collectors.toList()));
		return lista;
	}
	
	public void setUsuarios(Usuario usuario){
		this.repositorioUsuarios.setUsuarios(usuario);
		
	}
	public void setRepositorioUsuarios(UserRepository repo){
		this.repositorioUsuarios=repo;
	}

}
