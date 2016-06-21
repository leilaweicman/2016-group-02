package grupo2.tpAnual.OrigenesDeDatos;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import grupo2.tpAnual.Banco;
import grupo2.tpAnual.BancoExterno;
import grupo2.tpAnual.Comuna;
import grupo2.tpAnual.POI;
import grupo2.tpAnual.ServicioBancosExternos;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public class StubBancoExterno implements OrigenesDeDatos {
	public ObjectMapper mapper = new ObjectMapper();
	private BancoExterno bancoExterno;
	private ServicioBancosExternos mapaBancoExterno = new ServicioBancosExternos();

	@Override
	public List<POI> busqueda(String banco) {
		List<BancoExterno> json = new ArrayList<>();
		List<POI> listaPOI = new ArrayList<>();
		//json = this.mapaBancoExterno.busqueda(banco, "");
		listaPOI = transformarAPOI(json);
		return listaPOI;
	}

	public List<POI> transformarAPOI(List<BancoExterno> listaBancosExternos) {
		List<POI> listaPOI = new ArrayList<POI>();
		listaBancosExternos.forEach(banco -> listaPOI.add(adapter(bancoExterno)));
		return listaPOI;
	}

	private POI adapter(BancoExterno bancoExterno) {
		POI bancoPoi = new Banco();
		//Aca se setea cada item del banco externo con el poi
		//saqué la info de aca: http://www.anieto2k.com/2009/07/06/todo-lo-que-siempre-quisiste-saber-sobre-json/ 
		//para poder hacer el adapter. No estoy segura de que esté bien... 
		bancoPoi.setUbicacion(bancoExterno.x, bancoExterno.y);
		return bancoPoi;
	}


}
