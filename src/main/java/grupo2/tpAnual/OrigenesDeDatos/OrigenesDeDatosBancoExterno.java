package grupo2.tpAnual.OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ServiciosExternos.ServicioExternoBanco;
import grupo2.tpAnual.Banco;
import grupo2.tpAnual.POI;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	public ObjectMapper mapper = new ObjectMapper();
	private ServicioExternoBanco mapaBancoExterno;
	
	public OrigenesDeDatosBancoExterno(ServicioExternoBanco servicio){
		this.mapaBancoExterno = servicio; 
	}
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
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		List<POI> listaPOI = mapper.readValue(bancosExternosEnJson,
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, Banco.class));
		return listaPOI;
	}

}
