package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class MemoryUserRepository implements UserRepository {
	
	public List<Usuario> usuarios = new ArrayList<>();
	
	public void setUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	@Override
	public List<Usuario> getUsuariosByComuna(Comuna comuna) {
		return this.usuarios.stream().filter(usuario -> (usuario.getComuna().getNumeroComuna() == comuna.getNumeroComuna())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> getUsuariosByNombre(String nombre){
		return this.usuarios.stream().filter(usuario -> (usuario.getNombre().equals(nombre))).collect(Collectors.toList());
	}
	@Override
	public void deleteUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}
}
