package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.POIsRepository;
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
	
	public static ModelAndView listar(Request req, Response res) {
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = POIsRepository.instancia.listar();
		
		model.put("pois", pois);
		return new ModelAndView(model, "administracionTerminal.hbs");
		/*Map<String, List<Mapa>> model = new HashMap<>();
		List<Mapa> mapas = RepositorioProyectos.instancia.listar();
		
		model.put("proyectos", proyectos);
		return new ModelAndView(model, "proyectos/index.hbs");

		
		Map<String, List<POI>> model = new HashMap<>();
		String palabraBuscada = req.params("palabraBuscada");
		Mapa mapa = SingletonMapa.get();
		List<POI> resultadoBusqueda = new ArrayList<>();
		//resultadoBusqueda = mapa.busquedaRealizadaPorElUsuario(palabraBuscada);
		resultadoBusqueda = Arrays.asList(new Banco("Santander", Point.and(123.4, 223.4)), new Comercio("Zara",Point.and(87.777, 55.435),null), new CGP("Comuna 9", Point.and(255.4, 333.2)));
		model.put("pois",resultadoBusqueda);*/
		//return new ModelAndView(null, "listarTerminales.hbs");
	}
}
