package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	public List<Usuario> usuarios = new ArrayList<>();

	public void setUsuarios(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
}
