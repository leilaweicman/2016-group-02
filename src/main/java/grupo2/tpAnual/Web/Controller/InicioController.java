package grupo2.tpAnual.Web.Controller;

import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Models.SingletonUserRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {

	public static ModelAndView get(Request req, Response res){
		return new ModelAndView(null, "inicio.hbs");
	}
	
	public static ModelAndView home(Request req, Response res){
		String usuario = req.queryParams("usuario");
		UserRepository repo = SingletonUserRepository.get();
		Usuario user = repo.getUsuarioByNombre(usuario);
		if(user.getEsAdmin()) res.redirect("/admin");
		else res.redirect("/terminal");
		return null;
		
	}

}
