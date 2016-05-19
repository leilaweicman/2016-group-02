package grupo2.tpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IntegracionBancoExterno extends Integracion{
	private BancoExterno banco;
	
	@Override
	public List<POI> busqueda(String banco, String servicio) {
		String json;
		List<POI> listaPOI = new ArrayList<>();
		json = this.banco.busqueda(banco,servicio);
		listaPOI= transformarAPOI(json);
		return listaPOI;
	}
	
	/* { "banco": "Banco de la Plaza",
      "x": -35.9338322,
      "y": 72.348353,
      "sucursal": "Avellaneda",
      "gerente": "Javier Loeschbor",
      "servicios": [ "cobro cheques", "depósitos", "extracciones", "transferencias", "créditos", "", "", "" ]
   }*/
	
	public List<POI> transformarAPOI(String jsonBancosExt){
		ObjectMapper mapper = new ObjectMapper();
		try{
			List<POI> listaPOI = mapper.readValue(jsonBancosExt, List.class);
			return listaPOI;
		} catch (Exception e){
			//catcheo Exception generico solo por ahora
			throw new IntegracionBancoExternoException ("no se ha podido agregar", e);
		}
		
	}
	
	public void bancoExternoAPoi(BancoExterno banco){
		this.banco = banco;
	}
	
	public Point getUbicacion() {
		Point ubicacionBanco = new Point(banco.getX(), banco.getY());
		return ubicacionBanco;
	}
	
	
}
