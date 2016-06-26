package grupo2.tpAnual;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class BancoExternoTest {

	 public StubBancosExternos servicio;
	 @Before
	 public void init(){
		 servicio = new StubBancosExternos();
	 }
	 
	 @Test
	 public void verEnPantallaSiConvierteBien() throws JsonParseException, JsonMappingException, IOException {
		 List<BancoExterno> result = servicio.busqueda("s","s");
		 System.out.println(result);
	 }
}
