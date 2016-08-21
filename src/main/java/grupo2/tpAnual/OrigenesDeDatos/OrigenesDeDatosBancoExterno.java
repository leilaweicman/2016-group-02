package grupo2.tpAnual.OrigenesDeDatos;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.helpers.MapExtensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ServiciosExternos.ServicioExternoBanco;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrigenesDeDatosBancoExterno implements OrigenesDeDatos {
	public ObjectMapper mapper = new ObjectMapper();
	private ServicioExternoBanco mapaBancoExterno;
	
	public OrigenesDeDatosBancoExterno(ServicioExternoBanco servicio){
		this.mapaBancoExterno = servicio; 
	}
	@Override
	public List<POI> busqueda(String banco) {
		try {
			String json = this.mapaBancoExterno.busqueda(banco, "");
			
			return transformarAMap(json)
				.stream()
				.map((poiMap) -> adaptar(poiMap))
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private POI adaptar(Map<String, Object> map) {
		POI poi = MapExtensions.toObject(map, Banco.class);
		List<String> servicios = (List<String>) map.getOrDefault("servicios", new ArrayList<>());
		poi.setPalabrasClaves(servicios);
		return poi;
	}
	public List<Map<String, Object>> transformarAMap(String bancosExternosEnJson) throws Exception {		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		return mapper.readValue(bancosExternosEnJson,
				mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class));
	}

}
