package grupo2.tpAnual.OrigenesDeDatos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.StubBancosExternos;

import java.util.ArrayList;
import java.util.List;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	public ObjectMapper mapper = new ObjectMapper();
	private StubBancosExternos mapaBancoExterno = new StubBancosExternos();

	@Override
	public List<POI> busqueda(String banco) {
		List<POI> listaPOI = new ArrayList<>();
		try {
			String json = this.mapaBancoExterno.busqueda(banco, "");
			listaPOI = transformarAPOI(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPOI;
	}

	public List<POI> transformarAPOI(String bancosExternosEnJson) throws Exception {		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignora los atributos que no uso
		List<POI> listaPOI = mapper.readValue(bancosExternosEnJson, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Banco.class));
		return listaPOI;
	}

}
