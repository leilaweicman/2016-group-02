package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Models.SingletonUserRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionTerminalController {
	
	public static ModelAndView get(Request req, Response res) {
		Map<String, List<Usuario>> model = new HashMap<>();
		
		UserRepository repo = SingletonUserRepository.get();
		List<Usuario> terminales = repo.getUsauriosTerminal();
		
		model.put("terminales", terminales);
		return new ModelAndView(model, "administracionTerminal.hbs");
		
	}
	
	public static ModelAndView editar(Request req, Response res) {
		Map<String, Usuario> model = new HashMap<>();
		
		/*long id = req.queryParams("id");
		String id = req.headers("id");
		long idPosta = id.to*/
		List<Point> points = new ArrayList<Point>();
		points.add(Point.and(-34.664837, -58.385674));
		Comuna comuna1 = new Comuna(1, points);
		
		
		Usuario user = new Usuario();
		user.setNombre("Las heras");
		user.setComuna(comuna1);
		
		user.setComuna(comuna1);

		/*Comuna comuna2 = new Comuna(2, points);
				
		Usuario usuario = new Usuario();
		usuario.setNombre("Abasto");
		usuario.setComuna(comuna2);
		
		usuario.setComuna(comuna2);


		MemoryUserRepository repoUsuarios = new MemoryUserRepository();
		
		repoUsuarios.setUsuario(user);
		//repoUsuarios.setUsuario(usuario);
		
		List<Usuario> usuarios = repoUsuarios.getUsuarios();*/

		//List<Usuario> usuarios = SQLUserRepository.instance.getUsuarios();
		
		model.put("terminal", user);
		return new ModelAndView(model, "editarTerminal.hbs");
		
	}


	/* PARA NO OLVIDARME
	 * cada href de cada row deberia tener una forma de agarrar el id de la 
	 * terminal para poder editarla o eliminarla
	 * 
	 * no me agarra mi css
	 * 
	 * que onda con los repos, me tira null pointer exception, por eso harcodee
	 * 
	 * para editar, como es otra vista deberia hacer otro controller o desde este 
	 * mismo puedo meter un metodo editar que muestre otro .hbs?
	 * 
	 * ver por qeu si pongo href="/admin/terminal" tira 404 not found cuando en el router esta 
	 * puesto para que redireccione, con ? al final si funciona
	 * 
	 * tiene qeu estar hecho lo del repo para poder testearlo bien porque solo en la bd se crea
	 * el id para poder editarlo 
	 * 
	 * en base a la pregunta anterior, entonces, esto no funcionaria si no hy bd?
	 * 
	 * ver si en el model.put solo puedo mandar listas o si puedo mandar una sola cosa
	 * creo que puedo mandar una sola cosa tipo un usuario, pero no se como tomar sus atributos
	 */
	
}
