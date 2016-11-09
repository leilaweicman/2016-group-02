package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionTerminalController {
	
	public static ModelAndView get(Request req, Response res) {
		Map<String, List<Usuario>> model = new HashMap<>();
		
		List<Point> points = new ArrayList<Point>();
		points.add(Point.and(-34.664837, -58.385674));
		Comuna comuna1 = new Comuna(1, points);
		
		
		Usuario user = new Usuario();
		user.setNombre("Las heras");
		user.setComuna(comuna1);
		
		user.setComuna(comuna1);

		Comuna comuna2 = new Comuna(2, points);
				
		Usuario usuario = new Usuario();
		usuario.setNombre("Abasto");
		usuario.setComuna(comuna2);
		
		usuario.setComuna(comuna2);


		MemoryUserRepository repoUsuarios = new MemoryUserRepository();
		
		repoUsuarios.setUsuario(user);
		repoUsuarios.setUsuario(usuario);
		
		List<Usuario> usuarios = repoUsuarios.getUsuarios();

		//List<Usuario> usuarios = SQLUserRepository.instance.getUsuarios();
		
		model.put("terminales", usuarios);
		return new ModelAndView(model, "administracionTerminal.hbs");
		
	}
	
}
