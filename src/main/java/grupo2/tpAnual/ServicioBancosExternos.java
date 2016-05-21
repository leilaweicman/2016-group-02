package grupo2.tpAnual;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicioBancosExternos {

	public ObjectMapper mapper = new ObjectMapper();

	public List<BancoExterno> busqueda(String Banco, String Servicio)
			throws JsonParseException, JsonMappingException, IOException {
		// doc: https://github.com/FasterXML/jackson-databind/

		// Falta hacer llamado a la api
		// Por ahora setee el result para las pruebas

		String result = "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }," + " { \"banco\": \"Banco de la Plaza\"," + "   \"x\": -35.9345681," + "  \"y\": 72.344546,"
				+ "  \"sucursal\": \"Caballito\"," + "  \"gerente\": \"Fabián Fantaguzzi\","
				+ " \"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]"
				+ " }" + "]";

		List<BancoExterno> Bancos = mapper.readValue(result, List.class);
		return Bancos;
	}
}
