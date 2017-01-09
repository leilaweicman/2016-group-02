package grupo2.tpAnual.Web;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static boolean inMemory;
	
	public static void main(String[] args) {
		inMemory = false;
		Spark.port(4000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
