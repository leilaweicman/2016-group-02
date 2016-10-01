package grupo2.tpAnual.Repositorios;

import java.util.List;

import grupo2.tpAnual.Pois.Comuna;

public interface UserRepository {
	
	public void deleteUsuario(Usuario usuario);
	
	public void setUsuario(Usuario usuario);

	public List<Usuario> getUsuariosByComuna(Comuna comuna);

	public List<Usuario> getUsuarios();
}
