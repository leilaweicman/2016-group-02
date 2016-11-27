package grupo2.tpAnual.Web.Models;

import grupo2.tpAnual.Repositorios.MemoryUserRepository;
import grupo2.tpAnual.Repositorios.SQLUserRepository;
import grupo2.tpAnual.Repositorios.UserRepository;
import grupo2.tpAnual.Repositorios.Usuario;
import grupo2.tpAnual.Web.Server;

public class SingletonUserRepository {
	public static UserRepository instance;

	public static UserRepository get() {
		Usuario admin = new Usuario();
		Usuario terminal = new Usuario();

		admin.setEsAdmin(true);
		admin.setNombre("Administrador");

		terminal.setEsAdmin(false);
		terminal.setNombre("Terminal");

		if (instance == null) {
			if (Server.inMemory == true)
				instance = new MemoryUserRepository();
			else
				instance = SQLUserRepository.get();
		}
		instance.setUsuario(admin);
		instance.setUsuario(terminal);
		return instance;
	}

}
