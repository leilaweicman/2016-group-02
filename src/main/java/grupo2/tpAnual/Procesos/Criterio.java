package grupo2.tpAnual.Procesos;

import java.util.List;

import grupo2.tpAnual.Pois.Comuna;
import grupo2.tpAnual.Repositorios.Usuario;

public interface Criterio {
	public List<Usuario> dameUsuarios(Comuna comuna);
}
