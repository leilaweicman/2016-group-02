package grupo2.tpAnual.Procesos;

import java.util.List;

import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.Usuario;

public interface Criterio {
	//Lo implementan los tipos de criterio
	public List<Usuario> dameUsuarios(Comuna comuna);
	

}
