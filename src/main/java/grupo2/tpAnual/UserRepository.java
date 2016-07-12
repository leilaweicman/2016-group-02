package grupo2.tpAnual;

import java.util.List;

public class UserRepository {
	public List<Usuario> usuarios;
	
	public void setUsuarios(Usuario usuario){
		usuarios.add(usuario);
		
	}
	
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}

}
