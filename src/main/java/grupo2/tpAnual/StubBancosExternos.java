package grupo2.tpAnual;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StubBancosExternos {

	public ObjectMapper mapper = new ObjectMapper();

	public String busqueda(String Banco, String Servicio) throws Exception {

		String result = "[" + "{ \"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ " \"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
				+ " }," + " { \"banco\": \"Banco de la Plaza\"," + "   \"x\": -35.9345681," + "  \"y\": 72.344546,"
				+ "  \"sucursal\": \"Caballito\"," + "  \"gerente\": \"Fabián Fantaguzzi\","
				+ " \"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\" ]"
				+ " }" + "]";

		return result;
	}
}
