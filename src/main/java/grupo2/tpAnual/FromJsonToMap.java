package grupo2.tpAnual;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FromJsonToMap {
	public static ObjectMapper mapper = new ObjectMapper();

	public static List<Map<String, Object>> transformarAMap(String txtEnJson) throws Exception {		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
		return mapper.readValue(txtEnJson,mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class));
	}

}
