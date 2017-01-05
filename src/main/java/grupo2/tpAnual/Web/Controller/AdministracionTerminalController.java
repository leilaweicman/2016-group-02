package grupo2.tpAnual.Web.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import grupo2.tpAnual.AccesoriosPois.Comuna;
import grupo2.tpAnual.Observers.ObserverBusqueda;
import grupo2.tpAnual.Pois.POI;
import grupo2.tpAnual.Repositorios.ComunaRepository;
import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.ObserversRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Models.SingletonComunaRepository;
import grupo2.tpAnual.Web.Models.SingletonObserverRepository;
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
		return new ModelAndView(model, "admin/terminales/administracionTerminal.hbs");
		
	}
	
	public static ModelAndView editar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		long id = Long.parseLong(req.params("id"));
		
		UserRepository usuarios = SingletonUserRepository.get();
		
		Usuario user = usuarios.getUsuarioById(id);//Integer.parseInt(req.params("id")));
		
		ObserversRepository repo = SingletonObserverRepository.get();
		List<ObserverBusqueda> accionesDisponibles = repo.getObservers();
		List<ObserverBusqueda> accionesUsuario = user.getAccionesBusqueda();
		accionesDisponibles.removeAll(accionesUsuario);
		
		model.put("user", user);
		model.put("accionesDisponibles", accionesDisponibles);
		model.put("accionesUsuario", accionesUsuario);
		return new ModelAndView(model, "admin/terminales/editarTerminal.hbs");
		
	}

	public static ModelAndView guardar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		long id = Long.parseLong(req.params("id"));
		String nombre = req.params("nombre");
		int numeroComuna = Integer.parseInt(req.params("comuna"));
		//String acciones = req.params("acciones");
		
		ComunaRepository comunas = SingletonComunaRepository.get();
		Comuna comuna = comunas.getObserverByNumero(numeroComuna);
		

		UserRepository usuarios = SingletonUserRepository.get();
		
		Usuario user = usuarios.getUsuarioById(id);
		
		ObserversRepository repoAcciones = SingletonObserverRepository.get();
		List<ObserverBusqueda> acciones = repoAcciones.getObservers();
		//acciones.stream().forEach(accion-> this.editarAcciones(accion, user, req));
		
		user.setNombre(nombre);
		user.setComuna(comuna);
		
		usuarios.updateUsuario(user);
	
		return new ModelAndView(model, "admin/terminales/administracionTerminal.hbs");

	}

	/*public void editarAcciones(ObserverBusqueda observer, Usuario user, Request req){
		
		
		private int flag = Integer.parseInt(req.params(observer.getId())); 
	}*/
	
	
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
	 * en base a la pregunta anterior, entonces, esto no funcionaria si no hay bd?
	 * 
	 * ver si en el model.put solo puedo mandar listas o si puedo mandar una sola cosa
	 * creo que puedo mandar una sola cosa tipo un usuario, pero no se como tomar sus atributos
	 */
	
}
