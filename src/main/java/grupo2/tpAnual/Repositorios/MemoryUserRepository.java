package grupo2.tpAnual.Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo2.tpAnual.AccesoriosPois.Comuna;

public class MemoryUserRepository implements UserRepository {
	
	public List<Usuario> usuarios = new ArrayList<>();
	
	public void saveUser(Usuario usuario) {
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
	public Usuario getUsuarioByNombre(String nombre){
		return (this.usuarios.stream().filter(usuario -> (usuario.getNombre().equals(nombre))).collect(Collectors.toList())).get(0);
	}
	@Override
	public void deleteUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}

	@Override
	public List<Usuario> getUsauriosTerminal() {
		return this.usuarios.stream().filter(usuario -> (usuario.getEsAdmin() == false)).collect(Collectors.toList());
	}

	@Override
	public Usuario getUsuarioById(long id) {
		return (this.usuarios.stream().filter(usuario -> (usuario.getId()==id)).collect(Collectors.toList())).get(0);
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		usuarios.remove(usuario);
		usuarios.add(usuario);
	}
}
