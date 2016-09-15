package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class UserRepository {
	@ElementCollection @OneToMany @JoinColumn
	public List<Usuario> usuarios = new ArrayList<>();
	@Id @GeneratedValue
	private long id;
	
	public void setUsuarios(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
}
